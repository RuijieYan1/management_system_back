package com.yxtl.business.entity.second;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 收货地址表
 * </p>
 *
 * @author px
 * @since 2021-09-20
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="ProductAddr对象", description="收货地址表")
public class ProductAddr implements Serializable {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "收货地址表id")
      @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "公司id")
    private Integer companyId;

    @ApiModelProperty(value = "user.id")
    private Integer userId;

    @ApiModelProperty(value = "收货人")
    private String name;

    @ApiModelProperty(value = "省份")
    private String province;

    @ApiModelProperty(value = "城市")
    private String city;

    @ApiModelProperty(value = "区县")
    private String county;

    @ApiModelProperty(value = "详细地址")
    private String addressDetail;

    @ApiModelProperty(value = "地区编号")
    private String areaCode;

    @ApiModelProperty(value = "电话")
    private String tel;

    @ApiModelProperty(value = "是否默认地址 0：非默认，1：默认")
    private Integer isDefault;

    private LocalDateTime createTime;

    @ApiModelProperty(value = "选中状态 0选中 1未选中")
    private Integer isSelected;


}
