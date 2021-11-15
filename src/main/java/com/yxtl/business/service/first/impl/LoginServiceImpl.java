package com.yxtl.business.service.first.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.api.R;
import com.yxtl.business.constant.ResApiFailCode;
import com.yxtl.business.constant.ResApiSuccessCode;
import com.yxtl.business.dto.first.LoginDTO;
import com.yxtl.business.entity.first.Admin;
import com.yxtl.business.entity.first.User;
import com.yxtl.business.mapper.first.AdminMapper;
import com.yxtl.business.mapper.first.UserMapper;
import com.yxtl.business.service.first.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author px
 * @since 2021-09-19
 */
@Service
@DS("first")
public class LoginServiceImpl implements LoginService {

    @Autowired
    AdminMapper adminMapper;

    @Autowired
    UserMapper userMapper;

    @Override
    @DS("first")
    public R adminLogin(LoginDTO loginDTO) {
        Admin admin = null;
        String name = loginDTO.getName();
        String password = loginDTO.getPassword();
        System.out.println("输入的账号为: " + name + " 密码为: " + password);
        if (null == name) {
            return R.restResult(null, ResApiFailCode.NULL_NAME);
        }
        if (null == password) {
            return R.restResult(null, ResApiFailCode.NULL_PASSWORD);
        }
        Admin user = adminMapper.selectOne(new QueryWrapper<Admin>().lambda().eq(Admin::getAdminName, name));
        if (null != user) {
            admin = user;
            System.out.println("admin: " + admin);
        }
        if (null == admin) {
            return R.restResult(null, ResApiFailCode.ERROR_NAME);
        }
        if (!password.equals(admin.getAdminPassword())) {
            return R.restResult(null, ResApiFailCode.ERROR_PASSWORD);
        }
        return R.restResult(null, ResApiSuccessCode.SUCCESS);
    }

    @Override
    @DS("first")
    public R userLogin(LoginDTO loginDTO) {
        User user = null;
        String name = loginDTO.getName();
        String password = loginDTO.getPassword();
        System.out.println("输入的账号为: " + name + " 密码为: " + password);
        if (null == name) {
            return R.restResult(null, ResApiFailCode.NULL_NAME);
        }
        if (null == password) {
            return R.restResult(null, ResApiFailCode.NULL_PASSWORD);
        }
        User u = userMapper.selectOne(new QueryWrapper<User>().lambda().eq(User::getUserName, name));
        if (null != u) {
            user = u;
            System.out.println("user: " + user);
        }
        if (null == user) {
            return R.restResult(null, ResApiFailCode.ERROR_NAME);
        }
        if (!password.equals(user.getUserPassword())) {
            return R.restResult(null, ResApiFailCode.ERROR_PASSWORD);
        }
        return R.restResult(null, ResApiSuccessCode.SUCCESS);
    }
}
