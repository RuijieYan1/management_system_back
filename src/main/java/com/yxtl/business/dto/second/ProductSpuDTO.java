package com.yxtl.business.dto.second;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.math.BigDecimal;

/**
 * @author px
 * @date 2021/11/5 8:29
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="型号信息", description="型号信息")
public class ProductSpuDTO {

    @ApiModelProperty(value = "商品spu表id")
    private Integer id;

    @ApiModelProperty(value = "商品名称")
    private String name;

    @ApiModelProperty(value = "所属系列 0：风影  1：传说  2：鹰眼")
    private Integer series;

    @ApiModelProperty(value = "商品简介")
    private String sketch;

    @ApiModelProperty(value = "商品图片")
    private String img;

    @ApiModelProperty(value = "最低价")
    private BigDecimal minPrice;

    @ApiModelProperty(value = "销量")
    private Integer sales;

    @ApiModelProperty(value = "1上架 0下架")
    private Integer isSale;

    @ApiModelProperty(value = "1可见 0不可见")
    private Integer isShow;

}
