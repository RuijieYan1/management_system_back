package com.yxtl.business.service.first;

import com.baomidou.mybatisplus.extension.api.R;
import com.yxtl.business.entity.first.Message;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author px
 * @since 2021-11-01
 */
public interface MessageService extends IService<Message> {

    R unReadMessage(Integer currentPage);

    R readMessage(Integer currentPage);

    R checkStatus(Integer id);

    R checkAllStatus();

    R deleteMessage(Integer id);

    R clearAllStatus();

    R recycleMessage(Integer currentPage);

    R recoverMessage(Integer id);

}
