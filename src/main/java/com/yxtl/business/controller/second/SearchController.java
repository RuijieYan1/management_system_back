package com.yxtl.business.controller.second;

import com.baomidou.mybatisplus.extension.api.R;
import com.yxtl.business.service.second.SearchService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author px
 * @date 2021/9/24 11:59
 */
@Api(value = "搜索管理", tags = {"搜索管理"})
@Slf4j
@RestController
@RequestMapping("/search")
public class SearchController {

    @Autowired
    SearchService searchService;

    @ApiOperation(value = "公司搜索", notes = "公司搜索")
    @ApiResponses({
            @ApiResponse(code = 200, message = "执行成功"),
            @ApiResponse(code = -1, message = "出错了")
    })
    @GetMapping("/company")
//    public R searchCompany(@RequestParam String company) {
//        return searchService.searchCompany(company);
//    }
    public R searchCompany() {
        return searchService.searchCompany();
    }

    @ApiOperation(value = "账户搜索", notes = "账户搜索")
    @ApiResponses({
            @ApiResponse(code = 200, message = "执行成功"),
            @ApiResponse(code = -1, message = "出错了")
    })
    @GetMapping("/account")
    public R searchAccount(@RequestParam Integer account) {
        return searchService.searchAccount(account);
    }

}
