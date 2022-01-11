package com.yxtl.business.controller.second;


import com.baomidou.mybatisplus.extension.api.R;
import com.yxtl.business.dto.second.ExpressDTO;
import com.yxtl.business.service.second.ExpressService;
import com.yxtl.business.util.identity.IdentityUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author px
 * @since 2021-09-19
 */
@Api(value = "物流管理", tags = {"物流管理"})
@Slf4j
@RestController
@RequestMapping("/express")
public class ExpressController {

    @Autowired
    ExpressService expressService;

    @Autowired
    IdentityUtil identityUtil;

    @ApiOperation(value = "物流信息", notes = "物流信息")
    @ApiResponses({
            @ApiResponse(code = 200, message = "执行成功"),
            @ApiResponse(code = -1, message = "出错了")
    })
    @GetMapping("/getInfoByPage")
    public R expressInfo(@RequestParam Integer currentPage) {
        return expressService.expressInfo(currentPage);
    }

    @ApiOperation(value = "订单发货", notes = "订单发货")
    @ApiResponses({
            @ApiResponse(code = 200, message = "执行成功"),
            @ApiResponse(code = -1, message = "出错了")
    })
    @PostMapping("/deliver")
    public R deliverGoods(ExpressDTO expressDTO) {
        String name = identityUtil.getOperator(expressDTO.getUserName(), expressDTO.getIdentity());
        expressDTO.setOperator(name);
        return expressService.deliverGoods(expressDTO);
    }

}

