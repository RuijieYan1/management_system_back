package com.yxtl.business.dto.first;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;


/**
 * @author px
 * @date 2021/9/22 9:37
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="管理员信息", description="管理员信息")
public class UserDTO {

    @ApiModelProperty(value = "用户名")
    private String name;

    @ApiModelProperty(value = "用户账号")
    private String userName;

    @ApiModelProperty(value = "用户密码")
    private String userPassword;

}
