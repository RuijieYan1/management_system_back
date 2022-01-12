package com.yxtl.business.service.second.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.api.R;
import com.yxtl.business.constant.ResApiSuccessCode;
import com.yxtl.business.mapper.second.CompanyMapper;
import com.yxtl.business.mapper.second.ProductAccountMapper;
import com.yxtl.business.service.second.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author px
 * @date 2021/9/24 12:00
 */
@Service
@DS("second")
public class SearchServiceImpl implements SearchService {

    @Autowired
    CompanyMapper companyMapper;

    @Autowired
    ProductAccountMapper productAccountMapper;

    @Override
    @DS("second")
//    public R searchCompany(String company) {
//        List<String> list = companyMapper.searchCompany(company);
//        return R.restResult(list, ResApiSuccessCode.SUCCESS);
//    }
    public R searchCompany() {
        List<String> list = companyMapper.searchCompany();
        return R.restResult(list, ResApiSuccessCode.SUCCESS);
    }

    @Override
    @DS("second")
    public R searchAccount(Integer account) {
        List<Integer> list = productAccountMapper.searchAccount(account);
        return R.restResult(list, ResApiSuccessCode.SUCCESS);
    }

    @Override
    public R searchCompanyByName(String companyName) {
        List<String> list = companyMapper.searchCompanyByName(companyName);
        return R.restResult(list, ResApiSuccessCode.SUCCESS);
    }

    @Override
    public R searchCompanyUser(String userName,String companyName) {
        List<String> list = companyMapper.searchCompanyUser(userName,companyName);
        return R.restResult(list, ResApiSuccessCode.SUCCESS);
    }

}
