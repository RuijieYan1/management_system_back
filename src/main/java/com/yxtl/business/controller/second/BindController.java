package com.yxtl.business.controller.second;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.api.R;
import com.yxtl.business.constant.Constant;
import com.yxtl.business.constant.ResApiFailCode;
import com.yxtl.business.constant.ResApiSuccessCode;
import com.yxtl.business.dto.LoginIdentityDTO;
import com.yxtl.business.entity.first.Bind;
import com.yxtl.business.entity.second.Node;
import com.yxtl.business.service.first.BindService;
import com.yxtl.business.service.second.CompanyService;
import com.yxtl.business.service.second.NodeService;
import com.yxtl.business.util.excel.ExcelUtil;
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
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author px
 * @date 2021/9/22 16:20
 */
@Api(value = "入库管理", tags = {"入库管理"})
@Slf4j
@RestController
@RequestMapping("/bind")
public class BindController {

    @Autowired
    ExcelUtil excelUtil;

    @Autowired
    FileUtil fileUtil;

    @Autowired
    IdentityUtil identityUtil;

    @Autowired
    NodeService nodeService;

    @Autowired
    CompanyService companyService;

    @Autowired
    BindService bindService;


    @ApiOperation(value = "批量入库", notes = "批量入库")
    @ApiResponses({
            @ApiResponse(code = 200, message = "设备入库成功"),
            @ApiResponse(code = -8, message = "操作失败")
    })
    @PostMapping("/notes")
    @DS("second")
    public R bindNotes(@RequestParam(value = "file", required = false) MultipartFile file, String userName, String identify) {
        //入库失败的帽子
        List<String> failedList = new ArrayList<>();
        //文件上传 todo 修改Constant.BINDEXCELPATH的值
        File tempFile = fileUtil.transferToBindFile(file);
        //解析遍历excel文件
        Map<String, List<String>> excelAll = excelUtil.getExcelAll(tempFile);
        if (null == excelAll) {
            return R.restResult(null, ResApiFailCode.OPERATE_FAILED);
        }
        excelAll.forEach((companyName, nodeNumList) -> {
            List<String> list = bindService.storageDevice(companyName, nodeNumList);
            if (list.size() != 0) {
                failedList.addAll(list);
            }
        });
        if (excelAll.size() != 0) {
            Bind bind = new Bind();
            bind.setFileName(file.getOriginalFilename());
            bind.setExcel(tempFile.getAbsolutePath());
            bind.setType(Constant.BIND);
            bind.setTime(LocalDateTime.now());
            bind.setOperator(identityUtil.getOperator(userName, identify));
            bindService.bindNotes(bind);
            return R.restResult(failedList, ResApiSuccessCode.BIND_SUCCESS);
        } else {
            tempFile.delete();
            return R.restResult(null, ResApiFailCode.OPERATE_FAILED);
        }
    }



//    @ApiOperation(value = "批量解绑", notes = "批量解绑")
//    @ApiResponses({
//            @ApiResponse(code = 200, message = "设备解绑成功"),
//            @ApiResponse(code = -8, message = "操作失败")
//    })
//    @PostMapping("/relieve")
//    @DS("second")
//    public R relieveNotes(@RequestParam(value = "relieve",required = false) MultipartFile file, String companyName, String userName, String identify) {
////        Company company = companyService.getOne(new QueryWrapper<Company>().lambda().eq(Company::getName, companyName));
//        String realName = file.getOriginalFilename();
//        //查到的帽子
//        List<Node> list1 = new ArrayList<Node>();
//        //没查到的帽子
//        List<String> list2 = new ArrayList<>();
//        //不可解绑的帽子
//        List<Node> list3 = new ArrayList<>();
//        //可解绑的帽子
//        List<Node> list4 = new ArrayList<>();
//        File tempFile = fileUtil.transferToUnBindFile(file);
//        List list = excelUtil.getExcelAll(tempFile);
//        System.out.println("excel导入的内容为:" + list);
//        for (int i = 0; i < list.size(); i++) {
//            String str = (String) list.get(i);
//            Node node = nodeService.getOne(new QueryWrapper<Node>().lambda().eq(Node::getNodeNum, str));
//            if (null != node) {
//                list1.add(node);
//                if (null != node.getCompanyId() && null != node.getCreateTime()) {
//                    node.setCreateTime(null);
//                    node.setCompanyId(null);
//                    list4.add(node);
//                } else {
//                    list3.add(node);
//                }
//            } else {
//                list2.add(str);
//            }
//        }
//        System.out.println("查到的帽子编号有: " + list1 + "\n没查到的帽子编号有: " + list2 + "\n不可解绑的帽子编号: " + list3 + "\n可解绑的帽子编号: " + list4);
//        if (nodeService.updateBatchById(list4)) {
//            Bind bind = new Bind();
//            bind.setFileName(realName);
//            bind.setCompany(companyName);
//            bind.setExcel(tempFile.getAbsolutePath());
//            bind.setTime(LocalDateTime.now());
//            bind.setType(Constant.UNBIND);
//            String name = identityUtil.getOperator(userName, identify);
//            bind.setOperator(name);
//            bindService.unBindNotes(bind);
//            return R.restResult("没查到的帽子编号有:" + list2, ResApiSuccessCode.RELIEVE_SUCCESS);
//        } else {
//            tempFile.delete();
//            System.out.println("解绑失败--删除临时文件");
//            return R.restResult("没查到的帽子编号有:" + list2, ResApiFailCode.OPERATE_FAILED);
//        }
//    }

    @ApiOperation(value = "绑定记录", notes = "绑定记录")
    @ApiResponses({
            @ApiResponse(code = 200, message = "操作成功"),
            @ApiResponse(code = -1, message = "出错了")
    })
    @PostMapping("/bindRecord")
    @DS("first")
    public R bindRecord(@RequestParam Integer currentPage, LoginIdentityDTO loginIdentityDTO) {
//        System.out.println("loginIdentity" + loginIdentity + "currentPage" + currentPage);
        String name = identityUtil.getOperator(loginIdentityDTO.getUserName(), loginIdentityDTO.getIdentify());
        loginIdentityDTO.setName(name);
        return bindService.bindRecords(currentPage, loginIdentityDTO);
    }

    @ApiOperation(value = "解绑记录", notes = "解绑记录")
    @ApiResponses({
            @ApiResponse(code = 200, message = "操作成功"),
            @ApiResponse(code = -1, message = "出错了")
    })
    @PostMapping("/unBindRecord")
    @DS("first")
    public R unBindRecord(@RequestParam Integer currentPage, LoginIdentityDTO loginIdentityDTO) {
//        System.out.println("loginIdentity" + loginIdentity + "currentPage" + currentPage);
        String name = identityUtil.getOperator(loginIdentityDTO.getUserName(), loginIdentityDTO.getIdentify());
        loginIdentityDTO.setName(name);
        return bindService.unBindRecords(currentPage, loginIdentityDTO);
    }

    @ApiOperation(value = "绑定详情", notes = "绑定详情")
    @ApiResponses({
            @ApiResponse(code = 200, message = "操作成功"),
            @ApiResponse(code = -1, message = "出错了")
    })
    @GetMapping("/bindRecordDetail")
    @DS("first")
    public R bindRecordDetail(Integer id) {
        return bindService.bindRecordDetail(id);
    }
}
