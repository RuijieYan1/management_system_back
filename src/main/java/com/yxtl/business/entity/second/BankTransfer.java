package com.yxtl.business.entity.second;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author px
 * @since 2021-10-08
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="BankTransfer对象", description="")
public class BankTransfer implements Serializable {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "公对公转账记录表")
      @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "交易单号(第一位1在线支付，2对公转账，后三位随机数，中间时间戳到毫秒)")
    private String orderSn;

    @ApiModelProperty(value = "转账人手机号")
    private String tel;

    @ApiModelProperty(value = "打款账户开户名（公司名称或个人姓名）")
    private String name;

    @ApiModelProperty(value = "余额账户")
    private Long accountId;

    @ApiModelProperty(value = "打款账号")
    private String account;

    @ApiModelProperty(value = "打款银行")
    private String bankName;

    @ApiModelProperty(value = "申请充值金额")
    private BigDecimal amount;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @ApiModelProperty(value = "发起审核时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "实际充值金额")
    private BigDecimal realAmount;

    @ApiModelProperty(value = "审核状态：0待审核/1拒绝/2同意")
    private Integer reviewStatus;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @ApiModelProperty(value = "审核完成时间")
    private LocalDateTime reviewTime;

    @ApiModelProperty(value = "图片路径")
    private String img;

    @ApiModelProperty(value = "代充值人")
    private String operatorName;


}
