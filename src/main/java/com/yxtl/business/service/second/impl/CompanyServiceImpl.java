package com.yxtl.business.service.second.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yxtl.business.constant.Constant;
import com.yxtl.business.constant.ResApiFailCode;
import com.yxtl.business.constant.ResApiSuccessCode;
import com.yxtl.business.dto.PageInfoDTO;
import com.yxtl.business.dto.PublishDTO;
import com.yxtl.business.dto.second.CompanyDTO;
import com.yxtl.business.dto.second.NodeDTO;
import com.yxtl.business.entity.second.Company;
import com.yxtl.business.entity.second.ProductAccount;
import com.yxtl.business.mapper.second.CompanyMapper;
import com.yxtl.business.mapper.second.ProductAccountMapper;
import com.yxtl.business.service.second.CompanyService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yxtl.business.util.mqtt.Client;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * <p>
 * 公司表 服务实现类
 * </p>
 *
 * @author px
 * @since 2021-09-23
 */
@Service
@DS("second")
public class CompanyServiceImpl extends ServiceImpl<CompanyMapper, Company> implements CompanyService {

    @Autowired
    CompanyMapper companyMapper;

    @Autowired
    ProductAccountMapper productAccountMapper;

    @Override
    @DS("second")
    public R allCompanyInfo(Integer currentPage) {
        IPage<CompanyDTO> page = new Page<>(currentPage, 10);
        List<CompanyDTO> list = companyMapper.showCompanyByPage(page);
        System.out.println("公司信息记录总数: " + page.getTotal());
        PageInfoDTO pageInfoDTO = new PageInfoDTO();
        pageInfoDTO.setCompanyInfoList(list);
        pageInfoDTO.setCompanyTotal(page.getTotal());
        return R.restResult(pageInfoDTO, ResApiSuccessCode.SUCCESS);
    }

    @Override
    @DS("second")
    public R addCompany(CompanyDTO companyDTO) {
        Integer count = companyMapper.selectCount(new QueryWrapper<Company>().lambda().
                eq(Company::getName, companyDTO.getName()).or().eq(Company::getTel, companyDTO.getTel()));
        if (0 != count) {
            System.out.println("公司名或电话重复");
            return R.restResult(null, ResApiFailCode.ERROR_COMPANY);
        }
        Company company = new Company();
        company.setName(companyDTO.getName());
        company.setTel(companyDTO.getTel());
        String sixNum = companyDTO.getTel().substring(companyDTO.getTel().length() - 6);
        String password = DigestUtils.md5Hex(sixNum.getBytes());
        company.setPassword(password);
        companyMapper.insert(company);
        ProductAccount productAccount = new ProductAccount();
        productAccount.setCompanyId(company.getId());
        productAccount.setPassword(password);
        productAccountMapper.insert(productAccount);
        //mqtt发消息提醒公众号
        PublishDTO publishDTO = new PublishDTO();
        publishDTO.setData_type("creat_company");
        Map map = new HashMap();
        map.put("tel", company.getTel());
        map.put("name", company.getName());
        map.put("login_password", sixNum);
        map.put("pay_password", sixNum);
        List<Map> mqttData = new ArrayList<>();
        mqttData.add(map);
        publishDTO.setData(mqttData);
        System.out.println("发送的mqtt数据是: " + publishDTO);
        if (Client.publishCommunication(publishDTO)) {
            System.out.println("发送创建公司的提示信息成功");
        }
        return R.restResult(null, ResApiSuccessCode.SUCCESS);
    }


    @Override
    @DS("second")
    public R showNodeByCompany(String companyName) {
        List<NodeDTO> list = companyMapper.searchNodeByCompany(companyName);
        return R.restResult(list, ResApiSuccessCode.SUCCESS);
    }

    private String getPassword() {
        String str = "0123456789abcdefg";
        StringBuilder sb = new StringBuilder(8);
        int length = str.length();
        Random random = new Random();
        for (int i = 0; i < 8; i++) {
            char ch = str.charAt(random.nextInt(length));
            sb.append(ch);
        }
        return sb.toString();
    }


}
