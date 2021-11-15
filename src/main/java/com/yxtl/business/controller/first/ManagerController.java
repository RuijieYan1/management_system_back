package com.yxtl.business.controller.first;

import com.baomidou.mybatisplus.extension.api.R;
import com.yxtl.business.dto.first.UserDTO;
import com.yxtl.business.entity.first.User;
import com.yxtl.business.service.first.ManagerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author px
 * @date 2021/9/22 9:32
 */
@Api(value = "人员管理", tags = {"人员管理"})
@Slf4j
@RestController
@RequestMapping("/manager")
public class ManagerController {

    @Autowired
    ManagerService managerService;

    @ApiOperation(value = "获取管理员信息", notes = "获取管理员信息")
    @ApiResponses({
            @ApiResponse(code = 200, message = "执行成功"),
            @ApiResponse(code = -7, message = "该用户已存在")
    })
    @GetMapping("/getInfoByPage")
    public R allUserInfo(@RequestParam Integer currentPage) {
        return managerService.allUserInfo(currentPage);
    }

    @ApiOperation(value = "添加管理员", notes = "添加管理员")
    @ApiResponses({
            @ApiResponse(code = 200, message = "执行成功"),
            @ApiResponse(code = -1, message = "出错了")
    })
    @PostMapping("/add")
    public R addUser(@RequestBody UserDTO userDTO) {
        return managerService.addUser(userDTO);
    }

    @ApiOperation(value = "修改管理员信息", notes = "修改管理员信息")
    @ApiResponses({
            @ApiResponse(code = 200, message = "执行成功"),
            @ApiResponse(code = -1, message = "出错了")
    })
    @PostMapping("/edit")
    public R editUser(@RequestBody User user) {
        return managerService.editUser(user);
    }

    @ApiOperation(value = "删除管理员", notes = "删除管理员")
    @ApiResponses({
            @ApiResponse(code = 200, message = "执行成功"),
            @ApiResponse(code = -1, message = "出错了")
    })
    @DeleteMapping("/{id}")
    public R deleteUser(@PathVariable Integer id) {
        return managerService.deleteUser(id);
    }

}
