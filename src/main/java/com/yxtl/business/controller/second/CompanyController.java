package com.yxtl.business.controller.second;

import com.baomidou.mybatisplus.extension.api.R;
import com.yxtl.business.dto.second.CompanyDTO;
import com.yxtl.business.dto.second.NodeDTO;
import com.yxtl.business.service.second.CompanyService;
import com.yxtl.business.service.second.impl.CompanyServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author px
 * @date 2021/10/22 9:46
 */
@Api(value = "公司管理", tags = {"公司管理"})
@Slf4j
@RestController
@RequestMapping("/company")
public class CompanyController {

    @Autowired
    CompanyService companyService;

    @ApiOperation(value = "公司信息", notes = "公司信息")
    @ApiResponses({
            @ApiResponse(code = 200, message = "执行成功"),
            @ApiResponse(code = -1, message = "出错了")
    })
    @GetMapping("/getInfoByPage")
    public R allCompanyInfo(@RequestParam Integer currentPage) {
        return companyService.allCompanyInfo(currentPage);
    }

    @ApiOperation(value = "添加公司", notes = "添加公司")
    @ApiResponses({
            @ApiResponse(code = 200, message = "执行成功"),
            @ApiResponse(code = -1, message = "出错了")
    })
    @PostMapping("/addCompany")
    public R addCompany(@RequestBody CompanyDTO companyDTO) {
        return companyService.addCompany(companyDTO);
    }

    @ApiOperation(value = "公司的帽子信息", notes = "公司的帽子信息")
    @ApiResponses({
            @ApiResponse(code = 200, message = "执行成功"),
            @ApiResponse(code = -1, message = "出错了")
    })
    @GetMapping("/showNodeByCompany")
    public R showNodeByCompany(@RequestParam String companyName) {
        return companyService.showNodeByCompany(companyName);
    }

}
