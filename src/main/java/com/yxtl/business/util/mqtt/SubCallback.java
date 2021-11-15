package com.yxtl.business.util.mqtt;

import cn.hutool.core.date.DateUtil;
import com.yxtl.business.util.spring.SpringUtils;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.springframework.stereotype.Component;

/**
 * @author xzc
 * @date 2021/7/9 11:35
 */
@Slf4j
@Component
public class SubCallback implements MqttCallback {

    /**
     * 断开连接时调用，主要用于重连
     *
     * @param cause 失去连接的原因
     */
    @Override
    public void connectionLost(Throwable cause) {
        log.error("{}断开连接，错误日志是：{}，准备开始重连：", DateUtil.now(), cause.toString());
        int maxCount = 5;
        int currentCount = 0;
        while (currentCount < maxCount) {
            if (Client.init()) {
                log.info("重连成功！");
                try {
                    Client.subscribeTopic("s_Communication");
                    log.info("订阅成功！");
                    return;
                } catch (MqttException e) {
                    log.error("订阅失败！错误日志是：{}", e.toString());
                }
                currentCount = 5;
            }
            //这句注释掉就可以实现一直重连
            currentCount++;
        }
        log.error("重连失败！");
    }

    /**
     * 接收订阅的消息后执行的方法
     *
     * @param topic       MQTT主题
     * @param mqttMessage MQTT消息内容
     */
    @Override
    public void messageArrived(String topic, MqttMessage mqttMessage) {
        log.info("新收到mqtt,主题为：{}，消息为：{}", topic, mqttMessage);
        System.out.println("topic: " + topic + "\n mqttMessage: " + mqttMessage);
        MqttTaskService mqttTaskService = SpringUtils.getBean(MqttTaskService.class);
        String msg = new String(mqttMessage.getPayload());
        if ("s_Communication".equals(topic)) {
            mqttTaskService.doMessage(msg);
            return;
        }
        log.info("topic不符合约定格式");
    }

    /**
     * 接收到已经发布的 QoS 1 或 QoS 2 消息的传递令牌时调用
     * 消息发送成功后，调用此方法
     *
     * @param iMqttDeliveryToken 与消息关联的传递令牌
     */
    @SneakyThrows
    @Override
    public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {
        log.info("消息发送状态：{}", iMqttDeliveryToken.isComplete());
    }
}
