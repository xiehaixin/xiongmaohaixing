package com.xiongmaohaixin.annotation;

import java.lang.annotation.*;

/**
 * ClassName:ResponeNotAdvice
 * Package:com.evian.sqct.annotation
 * Description:加此注解 响应头参数就不经过 ResponseRestControllerAdvice 处理
 *
 * @Date:2020/6/11 13:49
 * @Author:XHX
 */
@Documented
@Inherited
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ResponseNotAdvice {
    boolean validate() default true;
}
