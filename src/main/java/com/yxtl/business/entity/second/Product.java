package com.yxtl.business.entity.second;

import java.math.BigDecimal;

import com.baomidou.mybatisplus.annotation.FieldStrategy;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
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
 * 商品sku表（库存量单元）
 * </p>
 *
 * @author px
 * @since 2021-11-03
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Product对象", description="商品sku表（库存量单元）")
public class Product implements Serializable {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "商品sku表id")
      @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "商品标题")
    private String name;

    @ApiModelProperty(value = "商品简介")
    private String sketch;

    @ApiModelProperty(value = "商品具体描述")
    private String intro;

    @ApiModelProperty(value = "种类 商品规格id  > product_kind.id")
    private Integer kindId;

    @ApiModelProperty(value = "型号 商品spuid  > product_spu.id")
    private Integer spuId;

    @ApiModelProperty(value = "所属系列 0：风影  1：传说  2：鹰眼")
    private Integer series;

    @ApiModelProperty(value = "商品图片")
    private String img;

    @ApiModelProperty(value = "商品价格")
    private BigDecimal price;

    @ApiModelProperty(value = "库存")
    private Integer stock;

    @ApiModelProperty(value = "销量")
    private Integer sales;

    @TableField(updateStrategy = FieldStrategy.IGNORED)
    @ApiModelProperty(value = "删除时间")
    private LocalDateTime deletedAt;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createdAt;

    @ApiModelProperty(value = "修改时间")
    private LocalDateTime updatedAt;

    @ApiModelProperty(value = "状态 0下架  1上架")
    private Integer isSale;

    @ApiModelProperty(value = "0不是套装 1是套装")
    private Integer isSet;


}
