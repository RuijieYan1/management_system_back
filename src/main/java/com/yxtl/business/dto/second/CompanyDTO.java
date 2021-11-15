package com.yxtl.business.dto.second;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * @author px
 * @date 2021/10/22 10:01
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="公司信息", description="公司信息")
public class CompanyDTO {

    @ApiModelProperty(value = "公司表id")
    private Integer id;

    @ApiModelProperty(value = "公司名称")
    private String name;

    @ApiModelProperty(value = "联系电话")
    private String tel;

    @ApiModelProperty(value = "公司密码")
    private String password;

    @ApiModelProperty(value = "公司账户")
    private Integer accountId;

    @ApiModelProperty(value = "公司id")
    private Integer companyId;

    @ApiModelProperty(value = "账户密码")
    private String accountPWD;

    @ApiModelProperty(value = "账户余额")
    private String balance;

}
