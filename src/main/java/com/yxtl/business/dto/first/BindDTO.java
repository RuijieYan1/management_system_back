package com.yxtl.business.dto.first;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

/**
 * @author px
 * @date 2021/10/18 15:18
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="绑定信息", description="绑定信息")
public class BindDTO {

    @ApiModelProperty(value = "记录表id")
    private Integer id;

    @ApiModelProperty(value = "文件真实名")
    private String fileName;

    @ApiModelProperty(value = "文件路径")
    private String excel;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @ApiModelProperty(value = "绑定/解绑时间")
    private LocalDateTime time;

    @ApiModelProperty(value = "0：绑定 1：解绑")
    private Integer type;

    @ApiModelProperty(value = "处理人")
    private String operator;

}
