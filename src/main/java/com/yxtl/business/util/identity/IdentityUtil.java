package com.yxtl.business.util.identity;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yxtl.business.constant.Constant;
import com.yxtl.business.entity.first.Admin;
import com.yxtl.business.entity.first.User;
import com.yxtl.business.mapper.first.AdminMapper;
import com.yxtl.business.mapper.first.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author px
 * @date 2021/10/19 10:54
 */
@Service
@DS("first")
public class IdentityUtil {

    @Autowired
    UserMapper userMapper;

    @Autowired
    AdminMapper adminMapper;

    @DS("first")
    public String getOperator(String username, String identify) {
        if (identify.equals(Constant.ADMIN)) {
            Admin admin = adminMapper.selectOne(new QueryWrapper<Admin>().lambda().eq(Admin::getAdminName, username));
            String name = admin.getName();
            System.out.println("操作人姓名: " + name);
            return name;
        } else {
            User user = userMapper.selectOne(new QueryWrapper<User>().lambda().eq(User::getUserName, username));
            String name = user.getName();
            System.out.println("操作人姓名: " + name);
            return name;
        }
    }

}
