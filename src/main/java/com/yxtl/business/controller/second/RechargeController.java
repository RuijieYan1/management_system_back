package com.yxtl.business.controller.second;

import com.baomidou.mybatisplus.extension.api.R;
import com.yxtl.business.constant.ResApiFailCode;
import com.yxtl.business.dto.LoginIdentityDTO;
import com.yxtl.business.dto.second.RechargeDTO;
import com.yxtl.business.service.second.RechargeService;
import com.yxtl.business.util.file.FileUtil;
import com.yxtl.business.util.identity.IdentityUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

/**
 * @author px
 * @date 2021/9/23 15:51
 */
@Api(value = "充值管理", tags = {"充值管理"})
@Slf4j
@RestController
@RequestMapping("/recharge")
public class RechargeController {

    @Autowired
    RechargeService rechargeService;

    @Autowired
    FileUtil fileUtil;

    @Autowired
    IdentityUtil identityUtil;

    @ApiOperation(value = "待处理充值订单", notes = "待处理充值订单")
    @ApiResponses({
            @ApiResponse(code = 200, message = "执行成功"),
            @ApiResponse(code = -1, message = "出错了")
    })
    @GetMapping("/rechargeList")
    public R rechargeList(Integer currentPage) {
        return rechargeService.rechargeList(currentPage);
    }

    @ApiOperation(value = "线下充值", notes = "线下充值")
    @ApiResponses({
            @ApiResponse(code = 200, message = "充值成功"),
            @ApiResponse(code = -9, message = "充值失败"),
            @ApiResponse(code = -10, message = "充值凭证不能为空"),
            @ApiResponse(code = -11, message = "该账户不存在"),
            @ApiResponse(code = -12, message = "充值金额不能为空")
    })
    @PostMapping("/offLine")
    public R offlineRecharge(@RequestParam(value = "imge", required = false) MultipartFile multipartFile, RechargeDTO rechargeDTO) {
        if (null != rechargeDTO.getAmount()) {
            File tempFile = fileUtil.transferToImage(multipartFile);
            System.out.println("tempFile.getAbsolutePath()" + tempFile.getAbsolutePath());
            rechargeDTO.setImg(tempFile.getAbsolutePath());
            String name = identityUtil.getOperator(rechargeDTO.getUserName(), rechargeDTO.getIdentify());
            rechargeDTO.setName(name);
            return rechargeService.offlineRecharge(rechargeDTO);
        } else {
            return R.restResult(null, ResApiFailCode.NULL_AMOUNT);
        }
    }

    @ApiOperation(value = "账户充值", notes = "账户充值")
    @ApiResponses({
            @ApiResponse(code = 200, message = "充值成功"),
            @ApiResponse(code = -9, message = "充值失败"),
            @ApiResponse(code = -10, message = "充值凭证不能为空"),
            @ApiResponse(code = -11, message = "该账户不存在"),
            @ApiResponse(code = -12, message = "充值金额不能为空")
    })
    @PostMapping("/online")
    public R onlineRecharge(@RequestParam(value = "imge", required = false) MultipartFile multipartFile, RechargeDTO rechargeDTO) {
        if (null != rechargeDTO.getAmount()) {
            File tempFile = fileUtil.transferToImage(multipartFile);
            System.out.println("tempFile.getAbsolutePath()" + tempFile.getAbsolutePath());
            rechargeDTO.setImg(tempFile.getAbsolutePath());
            String name = identityUtil.getOperator(rechargeDTO.getUserName(), rechargeDTO.getIdentify());
            rechargeDTO.setName(name);
            return rechargeService.onlineRecharge(rechargeDTO);
        } else {
            return R.restResult(null, ResApiFailCode.NULL_AMOUNT);
        }
    }

    @ApiOperation(value = "充值记录", notes = "充值记录")
    @ApiResponses({
            @ApiResponse(code = 200, message = "执行成功"),
            @ApiResponse(code = -1, message = "出错了")
    })
    @PostMapping("/record")
    public R rechargeRecord(@RequestParam Integer currentPage, LoginIdentityDTO loginIdentityDTO) {
        String name = identityUtil.getOperator(loginIdentityDTO.getUserName(), loginIdentityDTO.getIdentify());
        loginIdentityDTO.setName(name);
        return rechargeService.rechargeRecord(currentPage, loginIdentityDTO);
    }

    @ApiOperation(value = "查看凭证", notes = "查看凭证")
    @ApiResponses({
            @ApiResponse(code = 200, message = "执行成功"),
            @ApiResponse(code = -1, message = "出错了")
    })
    @GetMapping("/detail")
    public R recordDetail(@RequestParam Integer id) {
        return rechargeService.recordDetail(id);
    }

}
