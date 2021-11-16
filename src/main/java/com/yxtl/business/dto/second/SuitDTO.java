package com.yxtl.business.dto.second;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * @author px
 * @date 2021/11/16 10:45
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="套装信息", description="套装信息")
public class SuitDTO {

    @ApiModelProperty(value = "商品id")
    private Integer productId;

    @ApiModelProperty(value = "商品数量")
    private Integer amount;

    @ApiModelProperty(value = "套装id")
    private Integer suitId;

}
