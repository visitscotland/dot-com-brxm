package com.visitscotland.brmx.translation;

public class SpringContextFactory {
    public <T extends Object> T getBean(Class<T> beanClass) {
        return SpringContext.getBean(beanClass);
    }
}
