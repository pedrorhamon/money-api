package com.starking.moneyapi.config;

import java.util.Locale;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

public class AuthorizationServerConfig {
	
	@Bean
	public MessageSource message() {
		ReloadableResourceBundleMessageSource message = new ReloadableResourceBundleMessageSource();
		message.setBasename("classpath:messages");
		message.setDefaultEncoding("ISO-8859-1");
		message.setDefaultLocale(Locale.getDefault());
		return message;
	}
	
	@Bean
	public LocalValidatorFactoryBean validation(){
		LocalValidatorFactoryBean bean = new LocalValidatorFactoryBean();
		bean.setValidationMessageSource(message());
		return bean;
	}
}
