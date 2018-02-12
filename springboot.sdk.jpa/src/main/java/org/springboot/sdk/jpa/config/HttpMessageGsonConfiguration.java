package org.springboot.sdk.jpa.config;

import java.lang.reflect.Type;
import java.util.Date;

import org.fast.gson.annotation.GsonExclueDeserialize;
import org.fast.gson.annotation.GsonExclueSerialize;
import org.fast.gson.builder.GsonDateBuilder;
import org.springframework.http.converter.json.GsonHttpMessageConverter;

import springfox.documentation.spring.web.json.Json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializer;
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
		
		
		private boolean SET_DATE  = false;
		
		
	
	
		public HttpMessageGsonConfiguration registDateDeserializer(JsonDeserializer<Date> jsonDeserializer){
			SET_DATE = true;
			build.registerTypeAdapter(Date.class, jsonDeserializer);
			return this;
		}
		
		public HttpMessageGsonConfiguration registDateSerializer(JsonSerializer<Date> jsonSerializer){
			SET_DATE = true;
			build.registerTypeAdapter(Date.class, jsonSerializer);
			return this;
		}
		
		
	
		
	 	
	 	public GsonBuilder getBuild() {
			return build;
		}

		public void setBuild(GsonBuilder build) {
			this.build = build;
		}

		public GsonHttpMessageConverter create() {
	 		if(!SET_DATE){
	 			 build =  GsonDateBuilder.getBuilder(build);				
	 		}
	 		
	 		 build.registerTypeAdapter(Json.class, new JsonSerializer<Json>() {
	 			
					@Override
					public JsonElement serialize(Json src, Type typeOfSrc, JsonSerializationContext context) {
						JsonParser jsonParser = new JsonParser();										
						return jsonParser.parse(src.value());
					}			
				});
	 		 build
	    		.addSerializationExclusionStrategy(new GsonExclueSerialize())
	    		.addDeserializationExclusionStrategy(new GsonExclueDeserialize());
		
	 		Gson gson =  build.create();
			
		   
		
		    GsonHttpMessageConverter gsonConverter = new GsonHttpMessageConverter();
		    gsonConverter.setGson(gson);
		    return gsonConverter;
	    }
}
