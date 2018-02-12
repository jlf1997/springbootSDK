package org.springboot.sdk.jpa.config;

import java.lang.reflect.Type;

import org.fast.gson.annotation.GsonExclueDeserialize;
import org.fast.gson.annotation.GsonExclueSerialize;
import org.fast.gson.builder.GsonDateBuilder;
import org.springframework.http.converter.json.GsonHttpMessageConverter;

import springfox.documentation.spring.web.json.Json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

/**
 * 配置使用gson解析web
 * @author yx
 *
 */
public  class HttpMessageGsonConfiguration {
	
	
		private GsonBuilder build;
	
		private Gson gson;
		
		
		/**
		 * 自定义gson build属性
		 * @param build
		 */
		public  void setGsonBuilder(GsonBuilder build){
			this.build = build;
		}
		/**
		 * 自定义gson
		 * @param build
		 * @return
		 */
		public  void setGson(Gson gson){
			this.gson = gson;
		}
		
		
		public GsonBuilder getGsonBuilder(){
			return build;
		}
		
		public Gson getGson(){
			return gson;
		}
		
	 	
	 	public GsonHttpMessageConverter createGsonHttpMessageConverter() {
	 		if(build==null){
	 			 build = new GsonDateBuilder().getBuilder();
				 build.registerTypeAdapter(Json.class, new JsonSerializer<Json>() {
		
						@Override
						public JsonElement serialize(Json src, Type typeOfSrc, JsonSerializationContext context) {
							JsonParser jsonParser = new JsonParser();										
							return jsonParser.parse(src.value());
						}			
					});
	 		}		
			if(gson==null) {
				 gson = build
			    		.addSerializationExclusionStrategy(new GsonExclueSerialize())
			    		.addDeserializationExclusionStrategy(new GsonExclueDeserialize())
//			            .setDateFormat("yyyy-MM-dd HH:mm:ss")
			            .create();
			}
		   
		
		    GsonHttpMessageConverter gsonConverter = new GsonHttpMessageConverter();
		    gsonConverter.setGson(gson);
		    return gsonConverter;
	    }
}
