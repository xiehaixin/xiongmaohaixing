package com.xiongmaohaixin.annotation;

import java.lang.annotation.*;

/**
 * ClassName:DataNotLogCheck
 * Package:com.evian.sqct.annotation
 * Description:不进行aop日志记录返回值的注解
 *
 * @Date:2020/5/28 13:48
 * @Author:XHX
 */
@Documented
@Inherited
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface DataNotLogCheck {
	boolean validate() default true;
}