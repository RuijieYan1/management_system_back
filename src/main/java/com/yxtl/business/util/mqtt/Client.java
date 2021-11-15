package com.yxtl.business.util.mqtt;

import com.alibaba.fastjson.JSON;
import com.yxtl.business.dto.PublishDTO;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

import java.util.UUID;

/**
 * @author xzc
 * @date 2021/7/9 9:32
 */
@Slf4j
public class Client {

    public static final String HOST = "tcp://39.101.193.57:8883";
    static MqttClient client = null;


    public static boolean init() {
        try {
            String clientID = UUID.randomUUID().toString().replaceAll("-", "");
            log.info("客户端id是：{}", clientID);
            //匹配连接的服务器 MemoryPersistence设置clientid的保存形式，默认为以内存保存
            client = new MqttClient(HOST, clientID, new MemoryPersistence());
        } catch (Exception e) {
            log.error("初始化MQTT服务器出错：{}", e.toString());
            return false;
        }

        //设置连接的一些属性
        MqttConnectOptions options = new MqttConnectOptions();
        //设置是否清空session,这里如果设置为false表示服务器会保留客户端的连接记录，这里设置为true表示每次连接到服务器都以新的身份连接
        options.setCleanSession(true);
        options.setUserName("yxtl");
        options.setPassword("xtzggbmdkyxtl".toCharArray());
        options.setConnectionTimeout(30);
        options.setKeepAliveInterval(60);
        try {//连接服务器
            client.connect(options);
        } catch (Exception e) {
            log.error("连接MQTT服务器出错：{}", e.toString());
            return false;
        }
        return true;
    }


    /**
     * 订阅自定义主题消息
     *
     * @param topic gateway_num
     * @throws MqttException
     */
    public static void subscribeTopic(String topic) throws MqttException {
        log.info("订阅主题为：{},状态为{}", topic, client.isConnected());
        client.subscribe(topic, 2);
        client.setCallback(new SubCallback());
    }

    /**
     * 断开连接
     *
     * @return
     */
    public static boolean disconnect() {
        try {
            client.disconnect();
        } catch (MqttException e) {
            log.error("断开连接MQTT服务器出错：{}", e.toString());
        }
        return true;
    }

    /**
     * 向公众号发送消息
     *
     * @param publishDTO 发布的内容
     * @return true--发布消息成功； false--发布消息失败
     */
    public static boolean publishCommunication(PublishDTO publishDTO) {
        String payload = JSON.toJSONString(publishDTO);
        try {
            //retained属性表示是否需要向所有新订阅该主题的设备推送此消息
            client.publish("b_Communication", payload.getBytes(), 2, false);
        } catch (Exception e) {
            log.error("发布MQTT消息出错：{}", e.toString());
            return false;
        }
        return true;
    }

}
