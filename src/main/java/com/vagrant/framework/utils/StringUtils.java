package com.vagrant.framework.utils;

/**
 * @Description: 字符串拼接工具类
 */
public class StringUtils {
    private StringUtils() {

    }

    // userDao   ==>   setUserDao
    public static String getSetterMethodByFieldName(String fieldName) {
        String methodName = "set" + fieldName.substring(0,1).toUpperCase() + fieldName.substring(1);
        return methodName;
    }
}
