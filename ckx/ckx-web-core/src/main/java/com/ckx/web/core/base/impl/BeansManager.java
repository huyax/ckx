package com.ckx.web.core.base.impl;

import javax.annotation.PostConstruct;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class BeansManager implements ApplicationContextAware {

	public static ApplicationContext context;

	public static Object getBean(String beanName){
		return context.getBean(beanName);
	}
	
	public static <T> T getBean(Class<T> bean){
		return context.getBean(bean);
	}
	
	public static ApplicationContext getContext() {
		return context;
	}

	public void setApplicationContext(ApplicationContext context) throws BeansException {
		BeansManager.context = context;
	}
	
	@PostConstruct
    public void init() {
        SqlSessionTemplate template = getBean(SqlSessionTemplate.class);
        template.getConnection();
    }

}
