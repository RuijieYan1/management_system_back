package com.yxtl.business.dto.second;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @author px
 * @date 2021/11/4 14:34
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="商品信息", description="商品信息")
public class ProductDTO {

    @ApiModelProperty(value = "商品sku表id")
    private Integer id;

    @ApiModelProperty(value = "商品标题")
    private String name;

    @ApiModelProperty(value = "商品简介")
    private String sketch;

    @ApiModelProperty(value = "商品具体描述")
    private String intro;

    @ApiModelProperty(value = "种类 商品规格id  > product_kind.id")
    private Integer kindId;

    @ApiModelProperty(value = "规格id")
    private Integer kindProductId;

    @ApiModelProperty(value = "规格名称")
    private String kindName;

    @ApiModelProperty(value = "型号 商品spuid  > product_spu.id")
    private Integer spuId;

    @ApiModelProperty(value = "型号id")
    private Integer spuProductId;

    @ApiModelProperty(value = "型号名称")
    private String spuName;

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

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @ApiModelProperty(value = "删除时间")
    private LocalDateTime deletedAt;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createdAt;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @ApiModelProperty(value = "修改时间")
    private LocalDateTime updatedAt;

    @ApiModelProperty(value = "状态 0下架  1上架")
    private Integer isSale;

    @ApiModelProperty(value = "0不是套装 1是套装")
    private Integer isSet;

}
