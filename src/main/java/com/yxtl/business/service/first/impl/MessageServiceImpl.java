package com.yxtl.business.service.first.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yxtl.business.constant.Constant;
import com.yxtl.business.constant.ResApiFailCode;
import com.yxtl.business.constant.ResApiSuccessCode;
import com.yxtl.business.dto.PageInfoDTO;
import com.yxtl.business.entity.first.Message;
import com.yxtl.business.mapper.first.MessageMapper;
import com.yxtl.business.service.first.MessageService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author px
 * @since 2021-11-01
 */
@Service
@DS("first")
public class MessageServiceImpl extends ServiceImpl<MessageMapper, Message> implements MessageService {

    @Autowired
    MessageMapper messageMapper;

    @Autowired
    MessageService messageService;

    @Override
    @DS("first")
    public R unReadMessage(Integer currentPage) {
        IPage<Message> page = new Page<>(currentPage, 10);
        List<Message> list = messageMapper.showUnReadMsg(page);
        System.out.println("未读消息总数: " + page.getTotal());
        PageInfoDTO pageInfoDTO = new PageInfoDTO();
        pageInfoDTO.setUnReadMsgList(list);
        pageInfoDTO.setUnReadMsgTotal(page.getTotal());
        return R.restResult(pageInfoDTO, ResApiSuccessCode.SUCCESS);
    }

    @Override
    @DS("first")
    public R readMessage(Integer currentPage) {
        IPage<Message> page = new Page<>(currentPage, 10);
        List<Message> list = messageMapper.showReadMsg(page);
        System.out.println("已读消息总数: " + page.getTotal());
        PageInfoDTO pageInfoDTO = new PageInfoDTO();
        pageInfoDTO.setReadMsgList(list);
        pageInfoDTO.setReadMsgTotal(page.getTotal());
        return R.restResult(pageInfoDTO, ResApiSuccessCode.SUCCESS);
    }

    @Override
    @DS("first")
    public R checkStatus(Integer id) {
        Message message = messageMapper.selectOne(new QueryWrapper<Message>().lambda().eq(Message::getId, id));
        if (null != message) {
            message.setStatus(Constant.READ);
            message.setCompleteTime(LocalDateTime.now());
            messageMapper.updateById(message);
            return R.restResult(null, ResApiSuccessCode.SUCCESS);
        } else {
            return R.restResult(null, ResApiFailCode.FAILED);
        }
    }

    @Override
    @DS("first")
    public R checkAllStatus() {
        List<Message> list = messageMapper.selectList(new QueryWrapper<Message>().lambda().eq(Message::getStatus, Constant.UNREAD));
        System.out.println("一键前总数: " + list.size());
        for (int i = 0; i < list.size(); i++) {
            System.out.println("前-->list[" + i + "] " + list.get(i));
            list.get(i).setStatus(Constant.READ);
            list.get(i).setCompleteTime(LocalDateTime.now());
            System.out.println("后-->list[" + i + "] " + list.get(i) + "\n");
        }
        messageService.updateBatchById(list);
        return R.restResult(null, ResApiSuccessCode.SUCCESS);
    }

    @Override
    @DS("first")
    public R deleteMessage(Integer id) {
        Message message = messageMapper.selectOne(new QueryWrapper<Message>().lambda().eq(Message::getId, id));
        message.setStatus(Constant.DELETE);
        messageMapper.updateById(message);
        return R.restResult(null, ResApiSuccessCode.SUCCESS);
    }

    @Override
    @DS("first")
    public R clearAllStatus() {
        List<Message> list = messageMapper.selectList(new QueryWrapper<Message>().lambda().eq(Message::getStatus, Constant.READ));
        System.out.println("一键前总数: " + list.size());
        for (int i = 0; i < list.size(); i++) {
            System.out.println("前-->list[" + i + "] " + list.get(i));
            list.get(i).setStatus(Constant.DELETE);
            System.out.println("后-->list[" + i + "] " + list.get(i) + "\n");
        }
        messageService.updateBatchById(list);
        return R.restResult(null, ResApiSuccessCode.SUCCESS);
    }

    @Override
    @DS("first")
    public R recycleMessage(Integer currentPage) {
        IPage<Message> page = new Page<>(currentPage, 10);
        List<Message> list = messageMapper.showRecycleMsg(page);
        System.out.println("回收站消息总数: " + page.getTotal());
        PageInfoDTO pageInfoDTO = new PageInfoDTO();
        pageInfoDTO.setRecycleMsgList(list);
        pageInfoDTO.setRecycleMsgTotal(page.getTotal());
        return R.restResult(pageInfoDTO, ResApiSuccessCode.SUCCESS);
    }

    @Override
    @DS("first")
    public R recoverMessage(Integer id) {
        Message message = messageMapper.selectOne(new QueryWrapper<Message>().lambda().eq(Message::getId, id));
        message.setStatus(Constant.READ);
        messageMapper.updateById(message);
        return R.restResult(null, ResApiSuccessCode.SUCCESS);
    }

}
