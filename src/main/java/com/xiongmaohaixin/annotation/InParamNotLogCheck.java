package com.xiongmaohaixin.annotation;

import java.lang.annotation.*;

/**
 * ClassName:InParamNotLogCheck
 * Package:com.evian.sqct.annotation
 * Description:不进行aop日志记录入参参数的注解
 *
 * @Date:2020/5/28 13:48
 * @Author:XHX
 */
@Documented
@Inherited
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface InParamNotLogCheck {
    boolean validate() default true;
}