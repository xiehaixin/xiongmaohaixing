package com.xiongmaohaixin.annotation;

import java.lang.annotation.*;

/**
 * @date   2018年9月29日 下午2:46:42
 * @author XHX
 * @Description 视图层参数校验 是否有传此参数
 */
@Documented
@Inherited
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface DateCheck {
	
	boolean validate() default true;
	
	// 需要校验的参数
	String [] check() default {};
	
	// 可有可无的参数
	String [] unnecessary() default {};
}
