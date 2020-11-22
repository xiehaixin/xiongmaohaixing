package com.xiongmaohaixin.controllerAdvice;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.xiongmaohaixin.exception.BaseRuntimeException;
import com.xiongmaohaixin.exception.ResultException;
import com.xiongmaohaixin.response.ResultCode;
import com.xiongmaohaixin.response.ResultVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * @date   2019年9月3日 上午9:33:00
 * @author XHX
 * @Description controller层异常捕捉打日志并返回系统异常
 */
@RestControllerAdvice
public class ControllerAdviceException {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	/**
	 * 正则 判断数字
	 */
	private static final Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");
	
	@ExceptionHandler(Exception.class)
	public ResultVO<String> runtimeException(Exception e){
		logger.error("[project:{}] [exception:{}]", new Object[] {
				"xiongmaohaixing", e });
		return new ResultVO<String>(ResultCode.CODE_ERROR_SYSTEM,e.getMessage());
	}

	@ExceptionHandler(ResultException.class)
	public Map<String, Object> resultException(ResultException e){
		Map<String, Object> parMap = new HashMap<>();
		logger.error("[project:{}] [exception:{}]", new Object[] {
				"xiongmaohaixing", e });
		parMap.put("code", ResultCode.CUSTOM_CODE);
		parMap.put("message", e.getMessage());
		return parMap;
	}

	@ExceptionHandler(BindException.class)
	public Map<String, Object> bindException(BindException e){
		Map<String, Object> parMap = new HashMap<>();
		int code = ResultCode.CODE_ERROR_PARAM.getCode();
		String message = ResultCode.CODE_ERROR_PARAM.getMessage();
		parMap.put("code", code);
		parMap.put("message", message);
		logger.error(" [exception:{}] [message:{}]", new Object[] {message,e.getMessage()});
		return parMap;
	}


	@ExceptionHandler(BaseRuntimeException.ResultErrorCodeMessage.class)
	public Map<String, Object> runtimeException(BaseRuntimeException.ResultErrorCodeMessage e){
		Map<String, Object> parMap = new HashMap<>();
		logger.error("[project:{}] [exception:{}]", new Object[] {
				"xiongmaohaixing", e });
		String codeMessage = e.getMessage();
		String[] codeMessages = codeMessage.split(BaseRuntimeException.CODEMESSAGE);

		String code =codeMessages[0];

		if(isInteger(code)){
			parMap.put("code", Integer.parseInt(code));
		}else{
			parMap.put("code", code);
		}

		if(codeMessages.length>1){
			parMap.put("message", codeMessages[1]);
		}else{
			parMap.put("message", "");
		}

		return parMap;
	}

	/**
	 * 判断是否是数字
	 * @param str
	 * @return
	 */
	private static boolean isInteger(String str) {
		return pattern.matcher(str).matches();
	}

}
