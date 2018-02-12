package org.demo;

import java.lang.reflect.Type;
import java.util.Date;
import java.util.List;

import org.springboot.sdk.jpa.config.HttpMessageGsonConfiguration;
import org.springboot.sdk.jpa.controller.AbstractFastApplication;
import org.springboot.sdk.jpa.controller.FastApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.converter.HttpMessageConverter;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;





@SpringBootApplication
public class DemoApplication extends AbstractFastApplication{

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	

	@Override
	public void addConverters(List<HttpMessageConverter<?>> additionalConverters) {
		// TODO Auto-generated method stub
				HttpMessageGsonConfiguration gsonConfig = new HttpMessageGsonConfiguration();
				gsonConfig.getBuild().registerTypeAdapter(Date.class, new JsonSerializer<Date>(){

			
					@Override
					public JsonElement serialize(Date src, Type typeOfSrc, JsonSerializationContext context) {
						// TODO Auto-generated method stub
						return new JsonPrimitive(src.getTime()+"");
					}
					
				});
				additionalConverters.add(gsonConfig.create());
		
	}
	
	
	
	

	


	


}
