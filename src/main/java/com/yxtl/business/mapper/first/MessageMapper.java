package com.yxtl.business.mapper.first;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.yxtl.business.entity.first.Message;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author px
 * @since 2021-11-01
 */
public interface MessageMapper extends BaseMapper<Message> {

    List<Message> showUnReadMsg(IPage<Message> page);

    List<Message> showReadMsg(IPage<Message> page);

    List<Message> showRecycleMsg(IPage<Message> page);

}
