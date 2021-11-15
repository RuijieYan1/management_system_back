package com.yxtl.business.dto.second;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * @author px
 * @date 2021/11/3 16:57
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="规格信息", description="规格信息")
public class KindDTO {

    @ApiModelProperty(value = "编号")
    private Integer id;

    @ApiModelProperty(value = "规格名")
    private String name;

}
