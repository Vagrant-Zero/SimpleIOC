package com.vagrant.framework.beans.factory;

public interface BeanFactory {

    // 反射创建 bean 对象
    Object getBean(String name) throws Exception;

    <T> T getBean(String name, Class<? extends  T> clazz) throws Exception;

}
