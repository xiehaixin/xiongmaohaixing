package com.xiongmaohaixin.exception;

import com.xiongmaohaixin.response.ResultCode;

import java.io.Serializable;

/**
 * ClassName:BaseRunTimeException
 * Package:com.evian.sqct.exception
 * Description:自定义运行时异常
 * 定义内部类的好处
 * https://blog.csdn.net/weixin_34387284/article/details/93169490
 * 使用内部类作为自定义异常类的优点
 * 1.最显著的优点在于即使其它开发者写了一些难以读懂的错误信息，你也可以很清楚地弄懂具体错误是什么。
 * 2.你可以使用不同的异常实例来处理不同的异常场景
 * 3.你不需要使用单个异常来覆盖许多的异常情况
 * 4.编写否定的单元测试用例会更加容易
 * 5.日志会更加有意义以及高可读性
 *
 * @Date:2020/5/6 10:41
 * @Author:XHX
 */
public class BaseRuntimeException extends RuntimeException {

    public BaseRuntimeException(String msg) {
        super(msg);
    }


    public static final String CODEMESSAGE = "CodeMessage";

    /**
     * 返回错误代码和信息
     * 格式 xxCodeMessagexxx  例： 150CodeMessage查询不到数据
     */
    public static class ResultErrorCodeMessage extends BaseRuntimeException implements Serializable{
        private static final long serialVersionUID = 8737075844152666161L;

        public ResultErrorCodeMessage(String msg){
            super(msg);
        }

        public static String jointCodeMessage(String code,String message){
            StringBuilder result = new StringBuilder(code).append(CODEMESSAGE).append(message);
            return result.toString();
        }

        public static String jointCodeMessage(int code,String message){
            return jointCodeMessage(code+"",message);
        }

    }

    public static BaseRuntimeException.ResultErrorCodeMessage jointCodeAndMessage(String code,String message){
        return new BaseRuntimeException.ResultErrorCodeMessage(BaseRuntimeException.ResultErrorCodeMessage.jointCodeMessage(code,message));
    }

    public static BaseRuntimeException.ResultErrorCodeMessage jointCodeAndMessage(int code,String message){
        return new BaseRuntimeException.ResultErrorCodeMessage(BaseRuntimeException.ResultErrorCodeMessage.jointCodeMessage(code,message));
    }

    public static BaseRuntimeException.ResultErrorCodeMessage jointCodeAndMessage(ResultCode resultCode){
        return new BaseRuntimeException.ResultErrorCodeMessage(BaseRuntimeException.ResultErrorCodeMessage.jointCodeMessage(resultCode.getCode(), resultCode.getMessage()));
    }


}
