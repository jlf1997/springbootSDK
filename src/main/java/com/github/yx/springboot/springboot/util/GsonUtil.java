package com.github.yx.springboot.springboot.util;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonParser;

public class GsonUtil {
	/**
	 * json字符串转换未对应对象数组
	 * @param jsonstr
	 * @param cls
	 * @return
	 */
	public static <T> List<T> jsonToList(String jsonstr,Class<T> cls) {
		List<T> list = new ArrayList<T>();
		if(isJson(jsonstr)) {
			JsonElement returnData = new JsonParser().parse(jsonstr);
			Gson gs = new Gson(); 
			
			if(returnData.isJsonArray()) {
				JsonArray array = returnData.getAsJsonArray();
				for(JsonElement json : array) {					
					T t = gs.fromJson(json, cls);
					list.add(t);
				}
			}else {	
				if(returnData.isJsonObject()) {
					T t = gs.fromJson(returnData, cls);
					list.add(t);
				}
				
			}			
		}else {
			list =null;
		}
		return list;
		
	}
	/**
	 * 
	 * @param jsonstr
	 * @param cls
	 * @return
	 */
	public static <T> T jsonToObj(String jsonstr,Class<T> cls) {
		Gson gs = new Gson();  
		if(jsonstr==null || isJson(jsonstr))
			
			return gs.fromJson(jsonstr, cls);
		return null;
	}
	
	public static <T> String objToJson(T t) {
		Gson gs = new Gson();
		return gs.toJson(t);
	}
	
	public static boolean isJson(String json) {
		 try {  
	            new JsonParser().parse(json);  
	            return true;  
	        } catch (JsonParseException e) { 
	          
	            return false;  
	        }  
	}
	
	
	public static <T> JsonElement strToJsonElement(String jsonstr) {
		Gson gs = new Gson();
		return new JsonParser().parse(jsonstr);
	}
	
}
