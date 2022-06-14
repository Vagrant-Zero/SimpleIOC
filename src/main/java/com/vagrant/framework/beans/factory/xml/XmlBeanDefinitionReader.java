package com.vagrant.framework.beans.factory.xml;

import com.vagrant.framework.beans.BeanDefinition;
import com.vagrant.framework.beans.MutablePropertyValues;
import com.vagrant.framework.beans.PropertyValue;
import com.vagrant.framework.beans.factory.support.BeanDefinitionReader;
import com.vagrant.framework.beans.factory.support.BeanDefinitionRegistry;
import com.vagrant.framework.beans.factory.support.SimpleBeanDefinitionRegistry;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.dom4j.swing.BranchTreeNode;

import java.io.InputStream;
import java.util.List;


/**
 * BeanDefinitionReader的默认实现类
 */
public class XmlBeanDefinitionReader implements BeanDefinitionReader {

    private BeanDefinitionRegistry registry;

    public XmlBeanDefinitionReader() {
        this.registry = new SimpleBeanDefinitionRegistry();
    }

    @Override
    public BeanDefinitionRegistry getRegistry() {
        return registry;
    }

    @Override
    public void loadBeanDefinitions(String configLocation) throws Exception {
        // 使用dom4j解析xml文件
        SAXReader reader = new SAXReader();
        // 获取类路径下的配置文件
        ClassLoader classLoader = XmlBeanDefinitionReader.class.getClassLoader();
        InputStream is = classLoader.getResourceAsStream(configLocation);
        Document document = reader.read(is);
        // 使用document获取根标签对象
        Element rootElement = document.getRootElement();
        // 获取根标签下的所有bean标签
        List<Element> beans = rootElement.elements("bean");
        for(Element bean : beans) {
            String id = bean.attributeValue("id");
            String className = bean.attributeValue("class");
            // 根据属性创建 BeanDefinition对象
            BeanDefinition beanDefinition = new BeanDefinition();
            beanDefinition.setId(id);
            beanDefinition.setClassName(className);
            // 创建bean的属性
            MutablePropertyValues propertyValues = new MutablePropertyValues();
            List<Element> propertyElements = bean.elements("property");
            for(Element pe : propertyElements) {
                String name = pe.attributeValue("name");
                String ref = pe.attributeValue("ref");
                String value = pe.attributeValue("value");
                PropertyValue propertyValue = new PropertyValue(name, ref, value);
                propertyValues.addPropertyValue(propertyValue);
            }
            // 将属性设置到 beanDefinition 中
            beanDefinition.setPropertyValues(propertyValues);
            // 将 beanDefinition 注册到注册表中
            registry.registerBeanDefinition(id, beanDefinition);
        }

    }
}
