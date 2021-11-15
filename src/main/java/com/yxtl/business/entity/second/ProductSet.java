package com.yxtl.business.entity.second;

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
 * 
 * </p>
 *
 * @author px
 * @since 2021-11-03
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="ProductSet对象", description="")
public class ProductSet implements Serializable {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "商品套装关联表id")
      @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "套装的商品id ")
    private Integer setId;

    @ApiModelProperty(value = "对应的商品sku_id > product_sku.id")
    private Integer productId;

    @ApiModelProperty(value = "商品的数量")
    private Integer amount;


}
