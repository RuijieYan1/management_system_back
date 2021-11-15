package com.yxtl.business.controller.first;


import com.baomidou.mybatisplus.extension.api.R;
import com.yxtl.business.dto.first.LoginDTO;
import com.yxtl.business.service.first.LoginService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author px
 * @since 2021-09-19
 */
@Api(value = "登录管理", tags = {"登录管理"})
@Slf4j
@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    LoginService loginService;

    @ApiOperation(value = "超管登录", notes = "超管登录")
    @ApiResponses({
            @ApiResponse(code = 200, message = "执行成功"),
            @ApiResponse(code = -2, message = "账号为空"),
            @ApiResponse(code = -3, message = "账号错误"),
            @ApiResponse(code = -4, message = "密码为空"),
            @ApiResponse(code = -5, message = "密码错误")
    })
    @PostMapping("/adminLogin")
    public R adminLogin(@RequestBody LoginDTO loginDTO) {
        return loginService.adminLogin(loginDTO);
    }

    @ApiOperation(value = "普通登录", notes = "普通登录")
    @ApiResponses({
            @ApiResponse(code = 200, message = "执行成功"),
            @ApiResponse(code = -2, message = "账号为空"),
            @ApiResponse(code = -3, message = "账号错误"),
            @ApiResponse(code = -4, message = "密码为空"),
            @ApiResponse(code = -5, message = "密码错误")
    })
    @PostMapping("/userLogin")
    public R userLogin(@RequestBody LoginDTO loginDTO) {
        return loginService.userLogin(loginDTO);
    }

}

