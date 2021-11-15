package com.yxtl.business.dto.second;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @author px
 * @date 2021/9/23 16:01
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="充值信息", description="充值信息")
public class RechargeDTO {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "充值记录表id")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "订单编号")
    private String orderSn;

    @ApiModelProperty(value = "账户id")
    private Long accountId;

    @ApiModelProperty(value = "充值金额")
    private BigDecimal amount;

    @ApiModelProperty(value = "充值凭证")
    private byte[] image;

    @ApiModelProperty(value = "图片路径")
    private String img;

    @ApiModelProperty(value = "充值账号")
    private String userName;

    @ApiModelProperty(value = "账号身份")
    private String identify;

    @ApiModelProperty(value = "充值人")
    private String name;

    @ApiModelProperty(value = "交易完成时间")
    private LocalDateTime rechargeTime;

}
