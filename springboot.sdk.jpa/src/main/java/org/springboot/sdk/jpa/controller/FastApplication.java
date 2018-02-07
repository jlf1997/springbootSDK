package org.springboot.sdk.jpa.controller;

import java.lang.reflect.Type;
import java.util.List;

import org.gson.annotation.GsonExclueDeserialize;
import org.gson.annotation.GsonExclueSerialize;
import org.gson.builder.GsonDateBuilder;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.GsonHttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import springfox.documentation.spring.web.json.Json;

/**
 * 默认添加json转换
 * @author Administrator
 *
 */
@EnableJpaAuditing
public class FastApplication extends WebMvcConfigurerAdapter{
	
	
	@Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
		converters.add(createGsonHttpMessageConverter());
        super.configureMessageConverters(converters);
    }
	
	 private GsonHttpMessageConverter createGsonHttpMessageConverter() {
		 GsonBuilder build = new GsonDateBuilder().getBuilder();
		 build.registerTypeAdapter(Json.class, new JsonSerializer<Json>() {

				@Override
				public JsonElement serialize(Json src, Type typeOfSrc, JsonSerializationContext context) {
					// TODO Auto-generated method stub
					JsonParser jsonParser = new JsonParser();					
					
					return jsonParser.parse(src.value());
				}
				
			});
	        Gson gson = build
	        		.addSerializationExclusionStrategy(new GsonExclueSerialize())
	        		.addDeserializationExclusionStrategy(new GsonExclueDeserialize())
	                .setDateFormat("yyyy-MM-dd HH:mm:ss")
	                .create();

	        GsonHttpMessageConverter gsonConverter = new GsonHttpMessageConverter();
	        gsonConverter.setGson(gson);
	        return gsonConverter;
	    }
}
