package com.yxtl.business.entity.second;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;
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
 * @since 2021-09-19
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="ProductTransaction对象", description="")
public class ProductTransaction implements Serializable {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "主键id")
      @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "交易单号(第一位1在线支付，2对公转账，后三位随机数，中间时间戳到毫秒)")
    private String orderSn;

    @ApiModelProperty(value = "创建订单时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "下单的用户ID > user.id")
    private Integer userId;

    @ApiModelProperty(value = "公司id > company.id")
    private Integer companyId;

    @ApiModelProperty(value = "地址id>product_addr.id")
    private Integer addrId;

    @ApiModelProperty(value = "交易金额")
    private BigDecimal amount;

    @ApiModelProperty(value = "折扣")
    private Float discount;

    @ApiModelProperty(value = "需要发票 0为不需要 1为需要")
    private Integer needInvoice;

    @ApiModelProperty(value = "订单备注")
    private String comment;

    @ApiModelProperty(value = "支付状态 3取消 0未完成 1已完成 2异常")
    private Integer paymentStatus;

    @ApiModelProperty(value = "完成订单时间")
    private LocalDateTime completionTime;

    @ApiModelProperty(value = "订单状态 0待付款 1待发货 2待收货 3已取消 4已完成")
    private Integer orderStatus;

    @ApiModelProperty(value = "在线支付方式 1微信支付 2支付宝支付 3公对公 4账户余额 ")
    private Integer paymentType;

    @ApiModelProperty(value = "付款的用户ID > user.id")
    private Integer payUserId;

    @ApiModelProperty(value = "快递单号")
    private String logistics;

    @ApiModelProperty(value = "快递公司")
    private String expName;

    @ApiModelProperty(value = "发货人")
    private String deliverOperator;

    @ApiModelProperty(value = "发货时间")
    private LocalDateTime deliverTime;

    @ApiModelProperty(value = "收货状态 0未收获 1已收货")
    private Integer signStatus;

    @ApiModelProperty(value = "收货时间")
    private LocalDateTime signTime;


}
