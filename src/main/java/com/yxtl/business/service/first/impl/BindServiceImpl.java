package com.yxtl.business.service.first.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yxtl.business.constant.Constant;
import com.yxtl.business.constant.ResApiSuccessCode;
import com.yxtl.business.dto.LoginIdentityDTO;
import com.yxtl.business.dto.PageInfoDTO;
import com.yxtl.business.entity.first.Bind;
import com.yxtl.business.entity.second.Company;
import com.yxtl.business.entity.second.Node;
import com.yxtl.business.mapper.first.BindMapper;
import com.yxtl.business.mapper.second.CompanyMapper;
import com.yxtl.business.mapper.second.NodeMapper;
import com.yxtl.business.service.first.BindService;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author px
 * @since 2021-10-18
 */
@Service
public class BindServiceImpl extends ServiceImpl<BindMapper, Bind> implements BindService {

    @Autowired
    BindMapper bindMapper;

    @Autowired
    CompanyMapper companyMapper;

//    @Autowired
//    NodeMapper nodeMapper;

    @Autowired
    SqlSessionFactory sqlSessionFactory;

    @Override
    @DS("first")
    public R bindNotes(Bind bind) {
        bindMapper.insert(bind);
        return R.restResult(null, ResApiSuccessCode.SUCCESS);
    }

    @Override
    @DS("first")
    public R unBindNotes(Bind bind) {
        bindMapper.insert(bind);
        return R.restResult(null, ResApiSuccessCode.SUCCESS);
    }

    @Override
    @DS("first")
    public R bindRecords(Integer currentPage, LoginIdentityDTO loginIdentityDTO) {
        if (loginIdentityDTO.getIdentify().equals(Constant.ADMIN)) {
            IPage<Bind> page = new Page<>(currentPage, 10);
            List<Bind> list = bindMapper.adminShowBindInfo(page);
            System.out.println("超管绑定记录总数: " + page.getTotal());
            PageInfoDTO pageInfoDTO = new PageInfoDTO();
            pageInfoDTO.setBindRecordInfoList(list);
            pageInfoDTO.setBindRecordTotal(page.getTotal());
            return R.restResult(pageInfoDTO, ResApiSuccessCode.SUCCESS);
        } else {
            Page tePage = new Page(currentPage, 10);
            QueryWrapper<Bind> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("type", Constant.BIND);
            queryWrapper.eq("operator", loginIdentityDTO.getName());
            IPage<Bind> page = bindMapper.selectPage(tePage, queryWrapper.orderByDesc("id"));
            List<Bind> list = page.getRecords();
            System.out.println("普管绑定记录总数: " + page.getTotal());
            PageInfoDTO pageInfoDTO = new PageInfoDTO();
            pageInfoDTO.setBindRecordInfoList(list);
            pageInfoDTO.setBindRecordTotal(page.getTotal());
            return R.restResult(pageInfoDTO, ResApiSuccessCode.SUCCESS);
        }
    }

    @Override
    @DS("first")
    public R unBindRecords(Integer currentPage, LoginIdentityDTO loginIdentityDTO) {
        if (loginIdentityDTO.getIdentify().equals(Constant.ADMIN)) {
            IPage<Bind> page = new Page<>(currentPage, 10);
            List<Bind> list = bindMapper.adminShowUnBindInfo(page);
            System.out.println("超管解绑记录总数: " + page.getTotal());
            PageInfoDTO pageInfoDTO = new PageInfoDTO();
            pageInfoDTO.setUnBindRecordInfoList(list);
            pageInfoDTO.setUnBindRecordTotal(page.getTotal());
            return R.restResult(pageInfoDTO, ResApiSuccessCode.SUCCESS);
        } else {
            Page tePage = new Page(currentPage, 10);
            QueryWrapper<Bind> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("type", Constant.UNBIND);
            queryWrapper.eq("operator", loginIdentityDTO.getName());
            IPage<Bind> page = bindMapper.selectPage(tePage, queryWrapper.orderByDesc("id"));
            List<Bind> list = page.getRecords();
            System.out.println("普管解绑记录总数: " + page.getTotal());
            PageInfoDTO pageInfoDTO = new PageInfoDTO();
            pageInfoDTO.setUnBindRecordInfoList(list);
            pageInfoDTO.setUnBindRecordTotal(page.getTotal());
            return R.restResult(pageInfoDTO, ResApiSuccessCode.SUCCESS);
        }
    }

    @Override
    @DS("first")
    public R bindRecordDetail(Integer id) {
        String path = bindMapper.selectById(id).getExcel();
        path = path.replaceAll("\\\\", "/");
        return R.restResult(path, ResApiSuccessCode.SUCCESS);
    }

    @Override
    public List<String> storageDevice(String companyName, List<String> nodeNumList) {
        Company company = companyMapper.selectOne(new QueryWrapper<Company>().lambda().eq(Company::getName, companyName));
        List<String> failedList = new ArrayList<>();
        if (null == company) {
            return failedList;
        }
        SqlSession session = sqlSessionFactory.openSession(ExecutorType.BATCH);
        LocalDateTime now = LocalDateTime.now();
        try {
            NodeMapper nodeMapper = session.getMapper(NodeMapper.class);
            nodeNumList.forEach(nodeNum -> {
                Node node = new Node();
                node.setCompanyId(company.getId());
                node.setCreateTime(now);
                // ExecutorType为批量时返回-2147482646,而不是受影响的行数
                if (0 == nodeMapper.update(node, new QueryWrapper<Node>().lambda().eq(Node::getNodeNum, nodeNum).isNull(Node::getCompanyId))) {
                    failedList.add(nodeNum);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.commit();
            session.close();
        }
        return failedList;
    }

}
