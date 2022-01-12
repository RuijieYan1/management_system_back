package com.yxtl.business.service.second;

import com.baomidou.mybatisplus.extension.api.R;

/**
 * @author px
 * @date 2021/9/24 11:59
 */
public interface SearchService {

//    R searchCompany(String company);
    R searchCompany();

    R searchAccount(Integer account);

    R searchCompanyByName(String companyName);

    R searchCompanyUser(String userName,String companyName);
}
