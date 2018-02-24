package org.springboot.sdk.jpa.convert;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * @author yx
 * 
 * 是否使用gson作为解析器
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Import({HttpConvertConfiguration.class})
public @interface EnableHttpConvert {


}
