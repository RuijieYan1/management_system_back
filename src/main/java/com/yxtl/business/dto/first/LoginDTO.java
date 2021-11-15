package com.yxtl.business.dto.first;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * @Author: px
 * @Date: 2021/9/19/10:59
 * @Description:
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="登录信息", description="登录信息")
public class LoginDTO {

    @ApiModelProperty(value = "登录账号")
    private String name;

    @ApiModelProperty(value = "登录密码")
    private String password;

}
