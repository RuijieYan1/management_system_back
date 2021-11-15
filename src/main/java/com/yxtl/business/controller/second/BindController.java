package com.yxtl.business.controller.second;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.api.R;
import com.yxtl.business.constant.Constant;
import com.yxtl.business.constant.ResApiFailCode;
import com.yxtl.business.constant.ResApiSuccessCode;
import com.yxtl.business.dto.LoginIdentityDTO;
import com.yxtl.business.entity.first.Bind;
import com.yxtl.business.entity.second.Company;
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
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @author px
 * @date 2021/9/22 16:20
 */
@Api(value = "绑定管理", tags = {"绑定管理"})
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

    @ApiOperation(value = "批量绑定", notes = "批量绑定")
    @ApiResponses({
            @ApiResponse(code = 200, message = "设备入库成功"),
            @ApiResponse(code = -8, message = "操作失败")
    })
    @PostMapping("/notes")
    @DS("second")
    public R bindNotes(@RequestParam(value = "file",required = false) MultipartFile file, String companyName, String userName, String identify) throws IOException {
        Company company = companyService.getOne(new QueryWrapper<Company>().lambda().eq(Company::getName, companyName));
        String realName = file.getOriginalFilename();
        System.out.println("realName: " + realName);
        //查到的帽子
        List<Node> list1 = new ArrayList<>();
        //未查到的帽子
        List<String> list2 = new ArrayList<>();
        //不可绑(已绑定过)的帽子
        List<Node> list3 = new ArrayList<>();
        //可绑的帽子
        List<Node> list4 = new ArrayList<>();
        //文件上传
        File tempFile = fileUtil.transferToBindFile(file);
        //解析遍历excel文件
        List list = excelUtil.getExcelAll(tempFile);
        System.out.println("excel导入的内容为:" + list);
        for (int i = 0; i < list.size(); i++) {
            String str = (String) list.get(i);
            Node node = nodeService.getOne(new QueryWrapper<Node>().lambda().eq(Node::getNodeNum, str));
            if (null != node) {
                list1.add(node);
                if (null == node.getCompanyId()) {
                    node.setCreateTime(LocalDateTime.now());
                    node.setCompanyId(company.getId());
                    list4.add(node);
                } else {
                    list3.add(node);
                }
            } else {
                list2.add(str);
            }
        }
        System.out.println("查到的帽子有: " + list1 + "\n没查到的帽子有: " + list2 + "\n不可绑(已经绑定公司)的帽子: " + list3 + "\n可绑定的帽子(绑定成功): " + list4);
        if (nodeService.updateBatchById(list4)) {
            Bind bind = new Bind();
            System.out.println("绝对路径--tempFile.getAbsolutePath(): " + tempFile.getAbsolutePath());
            System.out.println("全路径--tempFile.getCanonicalPath(): " + tempFile.getCanonicalPath());
            bind.setFileName(realName);
            bind.setCompany(companyName);
            bind.setExcel(tempFile.getAbsolutePath());
            bind.setType(Constant.BIND);
            bind.setTime(LocalDateTime.now());
            String name = identityUtil.getUser(userName, identify);
            bind.setOperator(name);
            System.out.println("bind: " + bind);
            bindService.bindNotes(bind);
            return R.restResult("没查到的帽子编号为: " + list2, ResApiSuccessCode.BIND_SUCCESS);
        } else {
            tempFile.delete();
            System.out.println("绑定失败--删除临时文件");
            return R.restResult("没查到的帽子编号为: " + list2, ResApiFailCode.OPERATE_FAILED);
        }
    }

    @ApiOperation(value = "批量解绑", notes = "批量解绑")
    @ApiResponses({
            @ApiResponse(code = 200, message = "设备解绑成功"),
            @ApiResponse(code = -8, message = "操作失败")
    })
    @PostMapping("/relieve")
    @DS("second")
    public R relieveNotes(@RequestParam(value = "relieve",required = false) MultipartFile file, String companyName, String userName, String identify) {
//        Company company = companyService.getOne(new QueryWrapper<Company>().lambda().eq(Company::getName, companyName));
        String realName = file.getOriginalFilename();
        //查到的帽子
        List<Node> list1 = new ArrayList<Node>();
        //没查到的帽子
        List<String> list2 = new ArrayList<>();
        //不可解绑的帽子
        List<Node> list3 = new ArrayList<>();
        //可解绑的帽子
        List<Node> list4 = new ArrayList<>();
        File tempFile = fileUtil.transferToUnBindFile(file);
        List list = excelUtil.getExcelAll(tempFile);
        System.out.println("excel导入的内容为:" + list);
        for (int i = 0; i < list.size(); i++) {
            String str = (String) list.get(i);
            Node node = nodeService.getOne(new QueryWrapper<Node>().lambda().eq(Node::getNodeNum, str));
            if (null != node) {
                list1.add(node);
                if (null != node.getCompanyId() && null != node.getCreateTime()) {
                    node.setCreateTime(null);
                    node.setCompanyId(null);
                    list4.add(node);
                } else {
                    list3.add(node);
                }
            } else {
                list2.add(str);
            }
        }
        System.out.println("查到的帽子编号有: " + list1 + "\n没查到的帽子编号有: " + list2 + "\n不可解绑的帽子编号: " + list3 + "\n可解绑的帽子编号: " + list4);
        if (nodeService.updateBatchById(list4)) {
            Bind bind = new Bind();
            bind.setFileName(realName);
            bind.setCompany(companyName);
            bind.setExcel(tempFile.getAbsolutePath());
            bind.setTime(LocalDateTime.now());
            bind.setType(Constant.UNBIND);
            String name = identityUtil.getUser(userName, identify);
            bind.setOperator(name);
            bindService.unBindNotes(bind);
            return R.restResult("没查到的帽子编号有:" + list2, ResApiSuccessCode.RELIEVE_SUCCESS);
        } else {
            tempFile.delete();
            System.out.println("解绑失败--删除临时文件");
            return R.restResult("没查到的帽子编号有:" + list2, ResApiFailCode.OPERATE_FAILED);
        }
    }

    @ApiOperation(value = "绑定记录", notes = "绑定记录")
    @ApiResponses({
            @ApiResponse(code = 200, message = "操作成功"),
            @ApiResponse(code = -1, message = "出错了")
    })
    @PostMapping("/bindRecord")
    @DS("first")
    public R bindRecord(@RequestParam Integer currentPage, LoginIdentityDTO loginIdentityDTO) {
//        System.out.println("loginIdentity" + loginIdentity + "currentPage" + currentPage);
        String name = identityUtil.getUser(loginIdentityDTO.getUserName(), loginIdentityDTO.getIdentify());
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
        String name = identityUtil.getUser(loginIdentityDTO.getUserName(), loginIdentityDTO.getIdentify());
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
