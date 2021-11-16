package com.yxtl.business.dto;

import com.yxtl.business.dto.second.CompanyDTO;
import com.yxtl.business.dto.second.ExpressDTO;
import com.yxtl.business.dto.second.ProductDTO;
import com.yxtl.business.entity.first.Bind;
import com.yxtl.business.entity.first.Message;
import com.yxtl.business.entity.first.User;
import com.yxtl.business.entity.second.BankTransfer;
import com.yxtl.business.entity.second.Product;
import com.yxtl.business.entity.second.ProductKind;
import com.yxtl.business.entity.second.ProductSpu;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * @author px
 * @date 2021/10/13 14:45
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="分页信息", description="分页信息")
public class PageInfoDTO {
    @ApiModelProperty(value = "用户信息结果集")
    private List<User> list;

    @ApiModelProperty(value = "用户数量")
    private Long total;

    @ApiModelProperty(value = "物流信息结果集")
    private List<ExpressDTO> expInfoList;

    @ApiModelProperty(value = "物流信息数量")
    private Long expTotal;

    @ApiModelProperty(value = "充值信息结果集")
    private List<BankTransfer> rechargeInfoList;

    @ApiModelProperty(value = "充值信息数量")
    private Long rechargeTotal;

    @ApiModelProperty(value = "充值记录结果集")
    private List<BankTransfer> rechargeRecordInfoList;

    @ApiModelProperty(value = "充值记录数量")
    private Long rechargeRecordTotal;

    @ApiModelProperty(value = "绑定记录结果集")
    private List<Bind> bindRecordInfoList;

    @ApiModelProperty(value = "绑定记录数量")
    private Long bindRecordTotal;

    @ApiModelProperty(value = "解绑记录结果集")
    private List<Bind> unBindRecordInfoList;

    @ApiModelProperty(value = "解绑记录数量")
    private Long unBindRecordTotal;

    @ApiModelProperty(value = "公司信息结果集")
    private List<CompanyDTO> companyInfoList;

    @ApiModelProperty(value = "公司信息数量")
    private Long companyTotal;

    @ApiModelProperty(value = "未读消息结果集")
    private List<Message> unReadMsgList;

    @ApiModelProperty(value = "未读消息数量")
    private Long unReadMsgTotal;

    @ApiModelProperty(value = "已读消息结果集")
    private List<Message> readMsgList;

    @ApiModelProperty(value = "已读消息数量")
    private Long readMsgTotal;

    @ApiModelProperty(value = "回收站消息结果集")
    private List<Message> recycleMsgList;

    @ApiModelProperty(value = "回收站消息数量")
    private Long recycleMsgTotal;

    @ApiModelProperty(value = "商品型号结果集")
    private List<ProductSpu> spuList;

    @ApiModelProperty(value = "商品型号数量")
    private Long spuTotal;

    @ApiModelProperty(value = "商品种类结果集")
    private List<ProductKind> kindList;

    @ApiModelProperty(value = "商品种类数量")
    private Long kindTotal;

    @ApiModelProperty(value = "商品详情结果集")
    private List<ProductDTO> productList;

    @ApiModelProperty(value = "商品详情数量")
    private Long productTotal;

    @ApiModelProperty(value = "商品结果集")
    private List<Product> singleList;

    @ApiModelProperty(value = "商品数量")
    private Long singleTotal;

    @ApiModelProperty(value = "套装结果集")
    private List<Product> suitList;

    @ApiModelProperty(value = "套装数量")
    private Long suitTotal;

}
