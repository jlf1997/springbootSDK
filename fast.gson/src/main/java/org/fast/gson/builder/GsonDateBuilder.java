package org.fast.gson.builder;

import java.lang.reflect.Type;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

public class GsonDateBuilder {
	
	public GsonBuilder getBuilder() {
		
		GsonBuilder builder = new GsonBuilder();
		builder.registerTypeAdapter(Date.class, new JsonDeserializer<Date>() {

			@Override
			public Date deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
					throws JsonParseException {
				Date date = null;
				if (json.getAsJsonPrimitive().isNumber()) {
					date = new Date(json.getAsJsonPrimitive().getAsLong());
				} else {
					String string = json.getAsJsonPrimitive().getAsString();
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					try {
						date = sdf.parse(string);
					} catch (ParseException e) {
						

					}
				}

				return date;
			}
		});
		return builder;
	}
	
}
