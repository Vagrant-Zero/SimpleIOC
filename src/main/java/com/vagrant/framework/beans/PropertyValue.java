package com.vagrant.framework.beans;

/**
 * @Description: xml中属性对应的pojo
 */
public class PropertyValue {
    private String name; // 属性名称
    private String ref; // 属性为引用类型时：引用类型名称
    private String value; // 属性为基本类型时：类型名称

    public PropertyValue() {
    }

    public PropertyValue(String name, String ref, String value) {
        this.name = name;
        this.ref = ref;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRef() {
        return ref;
    }

    public void setRef(String ref) {
        this.ref = ref;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
