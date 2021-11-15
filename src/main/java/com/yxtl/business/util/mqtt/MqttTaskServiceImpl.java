package com.yxtl.business.util.mqtt;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.yxtl.business.constant.Constant;
import com.yxtl.business.entity.first.Message;
import com.yxtl.business.mapper.first.MessageMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Slf4j
@Service
public class MqttTaskServiceImpl implements MqttTaskService {

    @Autowired
    MessageMapper messageMapper;

    @Override
    public void doMessage(String msg) {
        log.info("需要进行处理的mqtt数据(充值订单)：{}", msg);
        JSONObject jsonObject = JSON.parseObject(msg);
        String dataType = (String) jsonObject.get("data_type");
        JSONArray dataList = jsonObject.getJSONArray("data");
        System.out.println("dataType: " + dataType);
        Message message = new Message();
        for (Object o : dataList) {
            JSONObject data = (JSONObject) o;
            System.out.println("order_sn: " + data.get("order_sn"));
            String orderSn = (String) data.get("order_sn");
            message.setOrderSn(orderSn);
            message.setStartTime(LocalDateTime.now());
            message.setStatus(Constant.UNREAD);
            if (dataType.equals("deliver_goods")) {
                message.setType(Constant.DELIVER_GOODS);
            }
            if (dataType.equals("pay_by_account")) {
                message.setType(Constant.RECHARGE);
            }
            log.info("message插入数据库: " + message);
            messageMapper.insert(message);
        }
    }

}