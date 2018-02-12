package org.demo;

import java.util.List;

import org.springboot.sdk.jpa.config.HttpMessageGsonConfiguration;
import org.springboot.sdk.jpa.controller.AbstractFastApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.converter.HttpMessageConverter;



@SpringBootApplication
public class DemoApplication extends AbstractFastApplication{

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Override
	public void addConverters(List<HttpMessageConverter<?>> arg0) {
		// TODO Auto-generated method stub
		HttpMessageGsonConfiguration gsonConfig = new HttpMessageGsonConfiguration();
//		gsonConfig.getBuild().registerTypeAdapter(arg0, new );
		additionalConverters.add(gsonConfig.create());
	}
	
	
	
	

	


	


}
