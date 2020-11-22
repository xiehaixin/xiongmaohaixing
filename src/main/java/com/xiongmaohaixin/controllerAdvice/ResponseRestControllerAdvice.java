package com.xiongmaohaixin.controllerAdvice;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.xiongmaohaixin.annotation.ResponseNotAdvice;
import com.xiongmaohaixin.exception.ResultException;
import com.xiongmaohaixin.response.ResultVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * ClassName:ResponseRestControllerAdvice
 * Package:com.evian.sqct.controllerAdvice
 * Description:将@RequestMapping注解的api返回值直接返回对象就可以，不需要返回状态码
 *
 * @Date:2020/6/11 11:40
 * @Author:XHX
 */
@RestControllerAdvice(basePackages ={"com.xiongmaohaixin.controller"})  // 注意哦，这里要加上需要扫描的包
public class ResponseRestControllerAdvice implements ResponseBodyAdvice<Object> {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * 是否需要校验
     * 加了 @ResponseNotAdvice 注解不需要校验
     */
    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {

        // 此注解不需要校验
        ResponseNotAdvice methodAnnotation = returnType.getMethodAnnotation(ResponseNotAdvice.class);
        if(methodAnnotation!=null&&methodAnnotation.validate()){
            return false;
        }
        // 如果接口返回的类型本身就是ResultVO那就没有必要进行额外的操作，返回false
        return !returnType.getParameterType().equals(ResultVO.class);
    }

    /**
     * Invoked after an {@code HttpMessageConverter} is selected and just before
     * its write method is invoked.
     *
     * @param returnType            the return type of the controller method
     * @param selectedContentType   the content type selected through content negotiation
     * @param selectedConverterType the converter type selected to write to the response
     * @param request               the current request
     * @param response              the current response
     * @return the body that was passed in or a modified (possibly new) instance
     */
    @Override
    public Object beforeBodyWrite(Object data, MethodParameter returnType, MediaType selectedContentType, Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        // String类型不能直接包装，所以要进行些特别的处理
        if (returnType.getGenericParameterType().equals(String.class)) {
            ObjectMapper objectMapper = new ObjectMapper();
            try {
                String result = (String)data;

                // 这是组织好的json串，直接响应就好
                if(result.contains("code")&&result.contains("message")){
                    response.getHeaders().setContentType(MediaType.APPLICATION_JSON_UTF8);
                    return data;
                }
                // 将数据包装在ResultVO里后，再转换为json字符串响应给前端
                return objectMapper.writeValueAsString(new ResultVO<>(data));
            } catch (JsonProcessingException e) {
                logger.error("data:{}",data);
                throw new ResultException("返回String类型错误");
            }
        }else if(data instanceof String){
            String result = (String)data;
            // 这是组织好的json串，直接响应就好
            if(result.contains("code")&&result.contains("message")){
                response.getHeaders().setContentType(MediaType.APPLICATION_JSON_UTF8);
                return data;
            }
        }
        // 将原本的数据包装在ResultVO里
        return new ResultVO<>(data);
    }
}
