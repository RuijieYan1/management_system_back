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


    //根据公司中用户姓名查询用户帽子信息
    R searchCompanyUser(String userName,String companyName);
}
