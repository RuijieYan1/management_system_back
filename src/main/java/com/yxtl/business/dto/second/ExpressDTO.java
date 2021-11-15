package com.yxtl.business.dto.second;

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
@ApiModel(value="物流信息", description="物流信息")
public class ExpressDTO {

    @ApiModelProperty(value = "编号")
    private Integer id;

    @ApiModelProperty(value = "订单编号")
    private String orderSn;

    @ApiModelProperty(value = "订单状态")
    private Integer orderStatus;

    @ApiModelProperty(value = "物流公司")
    private String expName;

    @ApiModelProperty(value = "物流编号")
    private String logistics;

    @ApiModelProperty(value = "发货时间")
    private String deliverTime;

    @ApiModelProperty(value = "签收状态")
    private Integer signStatus;

    @ApiModelProperty(value = "收货人")
    private String name;

    @ApiModelProperty(value = "手机号码")
    private String tel;

    @ApiModelProperty(value = "省份")
    private String province;

    @ApiModelProperty(value = "城市")
    private String city;

    @ApiModelProperty(value = "区县")
    private String county;

    @ApiModelProperty(value = "详细地址")
    private String addressDetail;

    @ApiModelProperty(value = "用户账号")
    private String userName;

    @ApiModelProperty(value = "用户身份")
    private String identity;

    @ApiModelProperty(value = "用户姓名")
    private String operator;

    @ApiModelProperty(value = "发货人")
    private String deliverOperator;

    @ApiModelProperty(value = "状态显示")
    private String showStatus;

}
