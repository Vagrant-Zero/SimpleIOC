package com.vagrant.framework.contex.support;

import com.vagrant.framework.beans.factory.support.BeanDefinitionReader;
import com.vagrant.framework.beans.factory.support.BeanDefinitionRegistry;
import com.vagrant.framework.contex.ApplicationContex;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public abstract class AbstractApplicationContext implements ApplicationContex {
    // beanDefinition解析器
    protected BeanDefinitionReader reader;

    // 存放bean的容器
    protected Map<String, Object> singletonObjects = new ConcurrentHashMap<>();

    // 配置文件的位置
    protected String configLocation;

    @Override
    public void refresh() throws Exception {
        // 加载xml中bean配置
        reader.loadBeanDefinitions(configLocation);
        // 进行bean的初始化
        finishBeanInitialization();
    }

    // bean的初始化
    private void finishBeanInitialization() throws Exception {
        BeanDefinitionRegistry registry = reader.getRegistry();
        String[] beanDefinitionNames = registry.getBeanDefinitionNames();
        for(String beanDefinitionName : beanDefinitionNames) {
            // 进行bean的初始化
            getBean(beanDefinitionName);
        }
    }
}
