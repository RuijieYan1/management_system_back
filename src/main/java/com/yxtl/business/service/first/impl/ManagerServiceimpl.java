package com.yxtl.business.service.first.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yxtl.business.constant.ResApiFailCode;
import com.yxtl.business.constant.ResApiSuccessCode;
import com.yxtl.business.dto.PageInfoDTO;
import com.yxtl.business.dto.first.UserDTO;
import com.yxtl.business.entity.first.User;
import com.yxtl.business.mapper.first.UserMapper;
import com.yxtl.business.service.first.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author px
 * @date 2021/9/22 9:34
 */
@Service
@DS("first")
public class ManagerServiceimpl implements ManagerService {

    @Autowired
    UserMapper userMapper;

    @Override
    @DS("first")
    public R allUserInfo(Integer currentPage) {
        IPage<User> page = new Page<>(currentPage, 10);
        List<User> list = userMapper.showInfoByPage(page);
//        System.out.println("总页数: " + page.getPages());
        System.out.println("人员信息记录总数: " + page.getTotal());
        PageInfoDTO pageInfoDTO = new PageInfoDTO();
        pageInfoDTO.setList(list);
        pageInfoDTO.setTotal(page.getTotal());
        return R.restResult(pageInfoDTO, ResApiSuccessCode.SUCCESS);
    }

    @Override
    @DS("first")
    public R addUser(UserDTO userDTO) {
        User u = userMapper.selectOne(new QueryWrapper<User>().lambda().eq(User::getName, userDTO.getName()));
        if (null != u) {
            return R.restResult(null, ResApiFailCode.ERROR_USER);
        } else {
            User u1 = userMapper.selectOne(new QueryWrapper<User>().lambda().eq(User::getUserName, userDTO.getUserName()));
            if (null != u1) {
                return R.restResult(null, ResApiFailCode.ERROR_USERNAME);
            } else {
                User user = new User();
                user.setName(userDTO.getName());
                user.setUserName(userDTO.getUserName());
                user.setUserPassword(userDTO.getUserPassword());
                userMapper.insert(user);
                return R.restResult(null, ResApiSuccessCode.SUCCESS);
            }
        }
    }

    @Override
    @DS("first")
    public R editUser(User user) {
        userMapper.updateById(user);
        return R.restResult(null, ResApiSuccessCode.SUCCESS);
    }

    @Override
    @DS("first")
    public R deleteUser(Integer id) {
        userMapper.deleteById(id);
        return R.restResult(null, ResApiSuccessCode.SUCCESS);
    }
}
