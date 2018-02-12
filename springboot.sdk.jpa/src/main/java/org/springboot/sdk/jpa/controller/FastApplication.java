package org.springboot.sdk.jpa.controller;

import java.util.List;

import org.springboot.sdk.jpa.config.HttpMessageGsonConfiguration;
import org.springframework.http.converter.HttpMessageConverter;

public class FastApplication extends AbstractFastApplication{

	@Override
	public void addConverters(List<HttpMessageConverter<?>> additionalConverters) {
		
		HttpMessageGsonConfiguration gsonConfig = new HttpMessageGsonConfiguration();
		additionalConverters.add(gsonConfig.create());
	}

}
