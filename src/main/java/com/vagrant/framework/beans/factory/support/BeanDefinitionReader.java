package com.vagrant.framework.beans.factory.support;

/**
 * @Description: BeanDefinition的解析器
 */
public interface BeanDefinitionReader {

    BeanDefinitionRegistry getRegistry();

    void loadBeanDefinitions(String configLocation) throws Exception;
}
