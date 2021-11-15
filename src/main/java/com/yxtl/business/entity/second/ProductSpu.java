package com.yxtl.business.entity.second;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 商品的标准化产品单元spu表(商品种类)
 * </p>
 *
 * @author px
 * @since 2021-11-03
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="ProductSpu对象", description="商品的标准化产品单元spu表(商品种类)")
public class ProductSpu implements Serializable {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "商品spu表id")
      @TableId(value = "id", type = IdType.AUTO)
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
