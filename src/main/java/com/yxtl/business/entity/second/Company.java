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
 * 公司表
 * </p>
 *
 * @author px
 * @since 2021-09-23
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Company对象", description="公司表")
public class Company implements Serializable {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "公司表id")
      @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "公司名")
    private String name;

    @ApiModelProperty(value = "昵称 设置后可用于登录")
    private String nickname;

    @ApiModelProperty(value = "注册时手机号可用于登录")
    private String tel;

    @ApiModelProperty(value = "统一社会信用代码")
    private String creditCode;

    @ApiModelProperty(value = "密码用于登录")
    private String password;

}
