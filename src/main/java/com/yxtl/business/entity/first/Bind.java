package com.yxtl.business.entity.first;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author px
 * @since 2021-10-18
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Bind对象", description="")
public class Bind implements Serializable {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "记录表id")
      @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "文件真实名")
    private String fileName;

    @ApiModelProperty(value = "公司名")
    private String company;

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
