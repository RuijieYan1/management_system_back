package com.yxtl.business.util.mqtt;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @author xzc
 * @date 2021/7/9 18:40
 */
@Slf4j
@Component
@Order
public class MqttClientImpl implements ApplicationRunner {

    @Override
    public void run(ApplicationArguments args) throws Exception {
       if (Client.init()) {
            // mqtt的订阅
            Client.subscribeTopic("s_Communication");
            return;
        }
        log.error("MQTT运行出错，请检查");
    }
}
