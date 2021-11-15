package com.yxtl.business.entity.second;

import java.math.BigDecimal;
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
 * 公司账户表
 * </p>
 *
 * @author px
 * @since 2021-09-24
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="ProductAccount对象", description="公司账户表")
public class ProductAccount implements Serializable {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "公司账户表id")
      @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "公司id  > company.id")
    private Integer companyId;

    @ApiModelProperty(value = "支付密码")
    private String password;

    @ApiModelProperty(value = "账户余额/积分余额")
    private BigDecimal balance;


}
