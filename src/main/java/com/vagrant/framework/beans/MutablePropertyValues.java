package com.vagrant.framework.beans;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @Description: MutablePropertyValues充当容器，包含多个PropertyValue
 *               PropertyValue中可能包含多个引用类型
 */
public class MutablePropertyValues {
    private final List<PropertyValue> propertyValueList;

    public MutablePropertyValues() {
        this.propertyValueList = new ArrayList<>();
    }

    public MutablePropertyValues(List<PropertyValue> propertyValueList) {
        if(propertyValueList == null) {
            this.propertyValueList = new ArrayList<>();
        }else {
            this.propertyValueList = propertyValueList;
        }
    }

    // 获取所有的PropertyValue
    public PropertyValue[] getPropertyValues() {
        return this.propertyValueList.toArray(new PropertyValue[0]);
    }

    // 根据名称获取PropertyValue
    public PropertyValue getPropertyValue(String propertyName) {
        if(propertyName == null) {
            throw new IllegalArgumentException("参数错误：bean的name为空。");
        }
        for(PropertyValue pv : propertyValueList) {
            if(propertyName.equals(pv.getName())) {
                return pv;
            }
        }
        return null;
    }

    // 当前MutablePropertyValues中是否有其他引用对象
    public boolean isEmpty() {
        return this.propertyValueList.isEmpty();
    }

    // 添加PropertyValue对象
    public void addPropertyValue(PropertyValue pv) {
        this.propertyValueList.add(pv);
    }

    // 判断是否含有指定 propertyName 属性值的对象
    public boolean contains(String propertyName) {
        if(propertyName == null) {
            throw new IllegalArgumentException("参数错误：bean的name为空。");
        }
        for(PropertyValue pv : propertyValueList) {
            if(propertyName.equals(pv.getName())) {
                return true;
            }
        }
        return false;
    }

    // 获取 PropertyValues 的迭代器
    public Iterator<PropertyValue> getIterator() {
        return this.propertyValueList.iterator();
    }
}
