package org.demo;

import org.springboot.sdk.jpa.convert.EnableHttpConvert;
import org.springboot.sdk.jpa.swagger.EnableSwagger2Doc;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;




@SpringBootApplication
@EnableSwagger2Doc
@EnableHttpConvert
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	

//	@Override
//	public void addConverters(List<HttpMessageConverter<?>> additionalConverters) {
//		// TODO Auto-generated method stub
//				HttpMessageGsonConfiguration gsonConfig = new HttpMessageGsonConfiguration();
//				gsonConfig.getBuild().registerTypeAdapter(Date.class, new JsonSerializer<Date>(){
//
//			
//					@Override
//					public JsonElement serialize(Date src, Type typeOfSrc, JsonSerializationContext context) {
//						// TODO Auto-generated method stub
//						return new JsonPrimitive(src.getTime()+"");
//					}
//					
//				});
//				additionalConverters.add(gsonConfig.create());
//		
//	}
	
	
	
	

	


	


}
