package com.xiongmaohaixin.annotation;

import java.lang.annotation.*;

/**
 * ClassName:LogNotCheck
 * Package:com.evian.sqct.annotation
 * Description:aop不记录入参和返回值日志的注解
 *
 * @Date:2020/5/28 13:53
 * @Author:XHX
 */
@Documented
@Inherited
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface LogNotCheck {
    boolean validate() default true;
}