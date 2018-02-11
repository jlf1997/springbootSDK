package org.springboot.sdk.jpa.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.autoconfigure.web.HttpMessageConverters;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.http.converter.HttpMessageConverter;

/**
 * 默认添加json转换
 * @author Administrator
 *
 */

@EnableJpaAuditing
@Configuration
public abstract class FastApplication {
	
	
	protected List<HttpMessageConverter<?>> additionalConverters;
	
	public abstract void addConverters(List<HttpMessageConverter<?>> additionalConverters);
	
	@Bean
    public HttpMessageConverters customConverters() {
		additionalConverters = new ArrayList<>();
		addConverters(additionalConverters);
        return new HttpMessageConverters(additionalConverters);
    }
	
}
