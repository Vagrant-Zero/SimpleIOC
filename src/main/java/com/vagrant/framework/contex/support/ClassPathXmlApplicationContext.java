package com.vagrant.framework.contex.support;

import com.vagrant.framework.beans.BeanDefinition;
import com.vagrant.framework.beans.MutablePropertyValues;
import com.vagrant.framework.beans.PropertyValue;
import com.vagrant.framework.beans.factory.support.BeanDefinitionReader;
import com.vagrant.framework.beans.factory.support.BeanDefinitionRegistry;
import com.vagrant.framework.beans.factory.xml.XmlBeanDefinitionReader;
import com.vagrant.framework.utils.StringUtils;

import java.lang.reflect.Method;

/**
 * 模板模式
 * getBean()方法延迟到该类（接口的实现类）才实现
 */
public class ClassPathXmlApplicationContext extends AbstractApplicationContext{

    public ClassPathXmlApplicationContext(String configLocation) {
        this.configLocation = configLocation;
        reader = new XmlBeanDefinitionReader();
        try {
            this.refresh();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Object getBean(String name) throws Exception {
        if (singletonObjects.get(name) != null) {
            return singletonObjects.get(name);
        }
        // 获取beanDefinition对象
        BeanDefinitionRegistry registry = reader.getRegistry();
        BeanDefinition beanDefinition = registry.getBeanDefinition(name);
        String className = beanDefinition.getClassName();
        // 反射创建对象
        Class<?> clazz = Class.forName(className);
        Object obj = clazz.newInstance();
        // DI
        MutablePropertyValues propertyValues = beanDefinition.getPropertyValues();
        for(PropertyValue pv : propertyValues.getPropertyValues()) {
            String propertyName = pv.getName();
            String propertyRef = pv.getRef();
            String propertyValue = pv.getValue();

            // 存在引用对象的属性
            if(propertyRef != null && !"".equals(propertyRef)) {
                Object bean = getBean(propertyName);
                String methodName = StringUtils.getSetterMethodByFieldName(propertyName);
                // 获取 obj（bean）的所有方法
                Method[] methods = clazz.getMethods();
                for(Method method : methods) {
                    if(methodName.equals(method.getName())) {
                        method.invoke(obj, bean);
                    }
                }
            }

            if(propertyValue != null && !"".equals(propertyValue)) {
                //拼接方法名
                String methodName = StringUtils.getSetterMethodByFieldName(propertyName);
                //获取method对象
                Method method = clazz.getMethod(methodName, String.class);
                method.invoke(obj, propertyValue);
            }

        }
        singletonObjects.put(name, obj);
        return obj;
    }

    @Override
    public <T> T getBean(String name, Class<? extends T> clazz) throws Exception {
        Object bean = getBean(name);
        if(bean == null) {
            return null;
        }
        return clazz.cast(bean);
    }
}
