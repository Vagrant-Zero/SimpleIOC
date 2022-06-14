package com.vagrant.framework.beans.factory.support;

import com.vagrant.framework.beans.BeanDefinition;

/**
 * @Description: BeanDefinition的注册表
 */
public interface BeanDefinitionRegistry {

    /**
     * 向注册表中注册bean
     * @param beanName
     * @param beanDefinition
     */
    void registerBeanDefinition(String beanName, BeanDefinition beanDefinition);

    /**
     * 从注册表中移除bean
     * @param beanName
     */
    void removeBeanDefinition(String beanName);

    /**
     * 根据beanName获取BeanDefinition
     * @param beanName
     * @return
     */
    BeanDefinition getBeanDefinition(String beanName);

    boolean containsBeanDefinition(String beanName);

    int getBeanDefinitionCount();

    String[] getBeanDefinitionNames();

}
