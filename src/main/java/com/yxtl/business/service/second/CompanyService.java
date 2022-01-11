package com.yxtl.business.service.second;

import com.baomidou.mybatisplus.extension.api.R;
import com.yxtl.business.dto.second.CompanyDTO;
import com.yxtl.business.entity.second.Company;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 公司表 服务类
 * </p>
 *
 * @author px
 * @since 2021-09-23
 */
public interface CompanyService extends IService<Company> {

    R allCompanyInfo(Integer currentPage);

    R addCompany(CompanyDTO companyDTO);

    //查询某公司中所有帽子的信息
    R showNodeByCompany(String companyName);

}
