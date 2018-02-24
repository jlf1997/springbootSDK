package org.demo.conver;

import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springboot.sdk.jpa.convert.gson.DefaultGsonConvert;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.GsonHttpMessageConverter;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

public class HttpMessageGsonDateConverter extends  DefaultGsonConvert{

	@Override
	public HttpMessageConverter setHttpMessageConverter() {
		GsonBuilder builder = getDefaultGsonBuilder();
		builder.registerTypeAdapter(Date.class, new JsonSerializer<Date>() {

			@Override
			public JsonElement serialize(Date src, Type typeOfSrc,
					JsonSerializationContext context) {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");		
				return new JsonPrimitive(sdf.format(src));
			}
			
			
		});
		Gson gson =  builder.create();		   	
	    GsonHttpMessageConverter gsonConverter = new GsonHttpMessageConverter();
	    gsonConverter.setGson(gson);
		return gsonConverter;
		
	}
	

	

	
	
}
