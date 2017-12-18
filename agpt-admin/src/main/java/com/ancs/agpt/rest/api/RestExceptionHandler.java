package com.ancs.agpt.rest.api;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.beans.ConversionNotSupportedException;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.ancs.agpt.rest.model.RestResult;

import lombok.extern.slf4j.Slf4j;


@ControllerAdvice
@Slf4j
public class RestExceptionHandler {
	
	/*@ExceptionHandler
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    private RestResult runtimeExceptionHandler(Exception e) {
		log.error("---------> huge error!", e);
		 return RestResult.error(e.getMessage());
		 new ResponseEntity<>(ResultModel.paramterError(bindingResult),HttpStatus.BAD_REQUEST);
        return RestResultGenerator.genErrorResult(ErrorCode.SERVER_ERROR);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    private RestResult illegalParamsExceptionHandler(MethodArgumentNotValidException e) {
    	log.error("---------> invalid request!", e);
    	BindingResult bindingResult = e.getBindingResult();
    	return RestResult.paramterError(bindingResult);
    }*/
	@ExceptionHandler(AccessDeniedException.class)
    @ResponseBody
    RestResult request403(HttpServletRequest request, Throwable ex) {
        HttpStatus status = getStatus(request);
        return RestResult.error(status.value(),ex.getMessage());
    }

    private HttpStatus getStatus(HttpServletRequest request) {
        Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
        if (statusCode == null) {
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return HttpStatus.valueOf(statusCode);
    }
	
	//运行时异常
    @ExceptionHandler(RuntimeException.class)  
    @ResponseBody  
    public RestResult runtimeExceptionHandler(RuntimeException runtimeException) {  
    	log.error(runtimeException.getMessage());
        return RestResult.error(1000,runtimeException.getMessage());
    }  

    //空指针异常
    @ExceptionHandler(NullPointerException.class)  
    @ResponseBody  
    public RestResult nullPointerExceptionHandler(NullPointerException ex) {  
    	log.error(ex.getMessage());
    	 return RestResult.error(1001,ex.getMessage());
    }   
    //类型转换异常
    @ExceptionHandler(ClassCastException.class)  
    @ResponseBody  
    public RestResult classCastExceptionHandler(ClassCastException ex) {  
    	log.error(ex.getMessage());
    	return RestResult.error(1002,ex.getMessage());
    }  

    //IO异常
    @ExceptionHandler(IOException.class)  
    @ResponseBody  
    public RestResult iOExceptionHandler(IOException ex) {
    	log.error(ex.getMessage());
    	return RestResult.error(1003,ex.getMessage());
    }  
    //未知方法异常
    @ExceptionHandler(NoSuchMethodException.class)  
    @ResponseBody  
    public RestResult noSuchMethodExceptionHandler(NoSuchMethodException ex) {  
    	log.error(ex.getMessage());
    	return RestResult.error(1004,ex.getMessage());
    }  

    //数组越界异常
    @ExceptionHandler(IndexOutOfBoundsException.class)  
    @ResponseBody  
    public RestResult indexOutOfBoundsExceptionHandler(IndexOutOfBoundsException ex) {  
    	log.error(ex.getMessage());
    	return RestResult.error(1005,ex.getMessage());
    }
    //400错误
    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseBody
    public RestResult requestNotReadable(HttpMessageNotReadableException ex){
    	log.error(ex.getMessage());
    	return RestResult.error(400,ex.getMessage());
    }
    //400错误
    @ExceptionHandler(TypeMismatchException.class)
    @ResponseBody
    public RestResult requestTypeMismatch(TypeMismatchException ex){
    	log.error(ex.getMessage());
    	return RestResult.error(400,ex.getMessage());
    }
    //400错误
    @ExceptionHandler(MissingServletRequestParameterException.class)
    @ResponseBody
    public RestResult requestMissingServletRequest(MissingServletRequestParameterException ex){
    	log.error(ex.getMessage());
    	return RestResult.error(400,ex.getMessage());
    }
    //405错误
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    @ResponseBody
    public RestResult request405(HttpRequestMethodNotSupportedException ex){
    	log.error(ex.getMessage());
    	return RestResult.error(405,ex.getMessage());
    }
    //406错误
    @ExceptionHandler(HttpMediaTypeNotAcceptableException.class)
    @ResponseBody
    public RestResult request406(HttpMediaTypeNotAcceptableException ex){
    	log.error(ex.getMessage());
    	return RestResult.error(406,ex.getMessage());
    }
    //500错误
    @ExceptionHandler({ConversionNotSupportedException.class,HttpMessageNotWritableException.class})
    @ResponseBody
    public RestResult server500(RuntimeException runtimeException){
    	log.error(runtimeException.getMessage());
    	return RestResult.error(runtimeException.getMessage());
    }
    
    
  //运行时异常
    @ExceptionHandler(Exception.class) 
    @ResponseBody  
    public RestResult runtimeExceptionHandler(Exception runtimeException) {  
    	log.error(runtimeException.getMessage());
        return RestResult.error(403,runtimeException.getMessage());
    } 
}
