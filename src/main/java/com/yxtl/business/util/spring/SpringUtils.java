package com.yxtl.business.util.spring;


import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Configuration;

/**
 * @author xzc
 * @date 2021/7/9 18:04
 */
@Configuration
public class SpringUtils implements ApplicationContextAware {

    private static ApplicationContext applCon;

    /**
     * 为空时，设置实例
     */
    @Override
    public void setApplicationContext(ApplicationContext applCon) throws BeansException {
        if (SpringUtils.applCon == null) {
            SpringUtils.applCon = applCon;
        }
    }

    /**
     * 获取applicationContext的实例applCon
     */
    public static ApplicationContext getApplicationContext() {
        return applCon;
    }

    /**
     * 通过name,以及Clazz获取指定的Bean
     */
    public static <T> T getBean(String name, Class<T> clazz) {
        return applCon.getBean(name, clazz);
    }

    /**
     * 通过clazz获取Bean（推荐使用这种）
     */
    public static <T> T getBean(Class<T> clazz) {
        return applCon.getBean(clazz);
    }

    /**
     * 通过name获取 Bean
     */
    public static Object getBean(String name) {
        return applCon.getBean(name);
    }
}