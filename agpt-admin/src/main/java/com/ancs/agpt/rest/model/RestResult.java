package com.ancs.agpt.rest.model;

import java.util.List;
import java.util.Map;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import com.ancs.agpt.enums.ResultStatus;
import static com.google.common.collect.Maps.*;
/**
 * 自定义返回结果
 */
public class RestResult {
	
	/**
     * 返回码
     */
    private int code;

    /**
     * 返回结果描述
     */
    private String message;

    /**
     * 返回内容
     */
    private Object content;
    
    public int getCode() {
  		return code;
  	}

  	public String getMessage() {
  		return message;
  	}

  	public Object getContent() {
  		return content;
  	}

    private RestResult(int code, String message) {
        this.code = code;
        this.message = message;
    }

    private RestResult(int code, String message, Object content) {
        this.code = code;
        this.message = message;
        this.content = content;
    }

    private RestResult(ResultStatus status) {
        this.code = status.getCode();
        this.message = status.getMessage();
    }

    private RestResult(ResultStatus status, Object content) {
        this.code = status.getCode();
        this.message = status.getMessage();
        this.content = content;
    }

    public static RestResult ok() {
        return new RestResult(ResultStatus._200());
    }
    
    public static RestResult warperOk(Object content) {
        return new RestResult(ResultStatus._200(),content);
    }

    public static RestResult paramterError(BindingResult result) {
    	List<FieldError> errors = result.getFieldErrors();
        Map error_map = newHashMap();
        for (FieldError e : errors) {
            error_map.put(e.getField().toString(), e.getDefaultMessage());
        }
        return new RestResult(ResultStatus._1000(),error_map);
    }
    
    
    public static RestResult error(String message) {
        return new RestResult(ResultStatus._500(),message);
    }
    
    public static RestResult error(int code ,String message) {
        return new RestResult(code,message);
    }

	public void setContent(Object content) {
		this.content = content;
	}
    
    
}
