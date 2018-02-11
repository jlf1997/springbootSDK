package org.springboot.sdk.jpa.config;

import java.lang.reflect.Type;

import org.gson.annotation.GsonExclueDeserialize;
import org.gson.annotation.GsonExclueSerialize;
import org.gson.builder.GsonDateBuilder;
import org.springframework.boot.autoconfigure.web.HttpMessageConverters;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.GsonHttpMessageConverter;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import springfox.documentation.spring.web.json.Json;

/**
 * 配置使用gson解析web
 * @author yx
 *
 */
public abstract class HttpMessageGsonConfiguration {
	
	
		private GsonBuilder build;
	
		private Gson gson;
		
		
		/**
		 * 自定义gson build属性
		 * @param build
		 */
		public abstract void setGsonBuilder(GsonBuilder build);
		/**
		 * 自定义gson
		 * @param build
		 * @return
		 */
		public abstract Gson setGson(GsonBuilder build);
		
		
	 	
	 	public GsonHttpMessageConverter createGsonHttpMessageConverter() {
			 build = new GsonDateBuilder().getBuilder();
			 build.registerTypeAdapter(Json.class, new JsonSerializer<Json>() {
	
					@Override
					public JsonElement serialize(Json src, Type typeOfSrc, JsonSerializationContext context) {
						// TODO Auto-generated method stub
						JsonParser jsonParser = new JsonParser();										
						return jsonParser.parse(src.value());
					}			
				});
			setGsonBuilder(build);
			gson = setGson(build);
			if(gson==null) {
				 gson = build
			    		.addSerializationExclusionStrategy(new GsonExclueSerialize())
			    		.addDeserializationExclusionStrategy(new GsonExclueDeserialize())
			            .setDateFormat("yyyy-MM-dd HH:mm:ss")
			            .create();
			}
		   
		
		    GsonHttpMessageConverter gsonConverter = new GsonHttpMessageConverter();
		    gsonConverter.setGson(gson);
		    return gsonConverter;
	    }
}
