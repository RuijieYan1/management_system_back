package com.yxtl.business.dto;

import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 * @author xzc
 * @date 2021/7/9 9:24
 */
@Data
public class PublishDTO {

    private String data_type;

    private List<Map> data;
}
