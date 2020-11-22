package com.xiongmaohaixin.annotation;

import java.lang.annotation.*;

/**
 * ClassName:TokenNotVerify
 * Package:com.evian.sqct.annotation
 * Description:token 验证注解 标上这个注解 该方法就不需要验证token
 *
 * @Date:2020/5/27 17:32
 * @Author:XHX
 */
@Documented
@Inherited
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface TokenNotVerify {
    boolean validate() default true;
}
