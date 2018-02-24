package org.springboot.sdk.jpa.config;

import java.lang.reflect.Type;

import org.fast.gson.annotation.GsonExclueDeserialize;
import org.fast.gson.annotation.GsonExclueSerialize;
import org.fast.gson.builder.GsonDateBuilder;
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
public  class HttpMessageGsonConfiguration {
	
	
		private GsonBuilder build;
		
		
		
		
		public HttpMessageGsonConfiguration(){			
			 build =  GsonDateBuilder.getBuilder(build);		
		}
		
		
	 	
	 	public GsonBuilder getBuild() {
			return build;
		}

		public void setBuild(GsonBuilder build) {
			this.build = build;
		}

		public GsonHttpMessageConverter create() {
	 		
	 		
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
