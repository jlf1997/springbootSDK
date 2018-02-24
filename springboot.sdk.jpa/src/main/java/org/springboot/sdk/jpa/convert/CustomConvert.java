package org.springboot.sdk.jpa.convert;

import org.springframework.http.converter.HttpMessageConverter;


public interface CustomConvert {

	public HttpMessageConverter setHttpMessageConverter();
}
