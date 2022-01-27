package com.yxtl.business.dto.second;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.List;
import java.util.Map;

/**
 * @Author: yrj
 * @Date: 2022/1/25
 * @Description:
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="线下订单信息", description="线下订单信息")
public class OfflineOrderDTO {

    @ApiModelProperty(value = "订单编号")
    private String orderSn;

    @ApiModelProperty(value = "商品id")
    private Integer productId;

    @ApiModelProperty(value = "商品规格id")
    private Integer productKind;

    @ApiModelProperty(value = "商品数量")
    private Integer productCount;

    @ApiModelProperty(value = "公司id")
    private Integer companyId;

    @ApiModelProperty(value = "公司名字")
    private String companyName;

    @ApiModelProperty(value = "收货人")
    private String name;

    @ApiModelProperty(value = "收货人电话")
    private String tel;

    @ApiModelProperty(value = "省份")
    private String province;

    @ApiModelProperty(value = "城市")
    private String city;

    @ApiModelProperty(value = "区县")
    private String county;

    @ApiModelProperty(value = "详细地址")
    private String addressDetail;

    @ApiModelProperty(value = "存储商品id和数量的list")
    private List<Map<String,String>> productList;


}
