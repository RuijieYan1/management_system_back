package com.yxtl.business.entity.second;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * <p>
 * 
 * </p>
 *
 * @author yrj
 * @since 2022-01-25
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="ProductOrderDetail对象", description="商品订单明细表")
public class ProductOrderDetail implements Serializable {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "交易id")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "订单id > product_transaction.order_sn")
    private String orderSn;

    @ApiModelProperty(value = "商品sku_id > product_sku.id")
    private Integer productId;

    @ApiModelProperty(value = "商品数量")
    private Integer productCount;

    @ApiModelProperty(value = "商品单价(当时下单的单价)")
    private BigDecimal price;

    @ApiModelProperty(value = "商品总价")
    private BigDecimal amount;

}
