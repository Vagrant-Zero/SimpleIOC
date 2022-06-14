package com.vagrant.framework.contex;

import com.vagrant.framework.beans.factory.BeanFactory;

public interface ApplicationContex extends BeanFactory {

    /**
     * 加载容器
     */
    void refresh() throws Exception;
}
