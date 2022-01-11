package com.yxtl.business.dto.second;

import com.baomidou.mybatisplus.annotation.FieldStrategy;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="帽子信息", description="帽子信息")
public class NodeDTO {

    @ApiModelProperty(value = "帽子编号")
    private String nodeNum;

    @ApiModelProperty(value = "节点编号用于与网关通信")
    private String chipId;

    @ApiModelProperty(value = "帽子系列 0风影 1传说 2鹰眼")
    private Integer series;

    @ApiModelProperty(value = "帽子颜色  yellow red blue white")
    private String helmetColor;

    @ApiModelProperty(value = "入库时间")
    @TableField(updateStrategy = FieldStrategy.IGNORED)
    private LocalDateTime createTime;

    @ApiModelProperty(value = "公司id  > company.id")
    @TableField(updateStrategy = FieldStrategy.IGNORED)
    private Integer companyId;

    @ApiModelProperty(value = "绑定的用户id > user.id")
    private Integer userId;

    @ApiModelProperty(value = "绑定的用户名> user.name")
    private String userName;

    @ApiModelProperty(value = "网关id  > gateway.id")
    private Integer gatewayId;

    @ApiModelProperty(value = "经度，默认东经")
    private BigDecimal lng;

    @ApiModelProperty(value = "纬度，默认北纬")
    private BigDecimal lat;

    @ApiModelProperty(value="最后更新时间")
    private String lastTime;

    @ApiModelProperty(value = "连接状态 0离线  1在线")
    private Integer isLink;

    @ApiModelProperty(value = "电池电量")
    private Integer bat;

    @ApiModelProperty(value = "听和说 1开 0关 第一位听 第二位说")
    private String intercomStatus;

    @ApiModelProperty(value = "sos状态 0正常状态 1sos状态")
    private Integer isSos;

    @ApiModelProperty(value = "到期状态   0:未到期， 1:到期")
    private Integer isExpired;

}
