package com.ancs.agpt.security.config;

import java.io.IOException;

import org.springframework.http.HttpOutputMessage;
import org.springframework.http.converter.HttpMessageNotWritableException;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import com.ancs.agpt.rest.model.RestResult;
import com.ancs.agpt.system.toolkit.AESUtil;

import springfox.documentation.spring.web.json.Json;

public class AncsHttpMessageConverter extends FastJsonHttpMessageConverter{
	
	public AncsHttpMessageConverter() {
        super();
        this.getFastJsonConfig().getSerializeConfig().put(Json.class, SwaggerJsonSerializer.instance);
    }
	private boolean enableEncrypt;
	
	@Override
    protected void writeInternal(Object obj, HttpOutputMessage outputMessage)
            throws IOException, HttpMessageNotWritableException {
        if(enableEncrypt && obj.getClass().isAssignableFrom(RestResult.class)) {
        	
            try {
            	RestResult result = (RestResult) obj;
            	FastJsonConfig fastJsonConfig = getFastJsonConfig();
            	String jsonContent = JSON.toJSONString(result.getContent(), fastJsonConfig.getSerializeConfig(), fastJsonConfig.getSerializeFilters(), fastJsonConfig.getDateFormat(), JSON.DEFAULT_GENERATE_FEATURE, fastJsonConfig.getSerializerFeatures());
            	String jsonEncrypt = AESUtil.encrypt(jsonContent);
            	result.setContent(jsonEncrypt);
            	String json = JSON.toJSONString(result, fastJsonConfig.getSerializeConfig(), fastJsonConfig.getSerializeFilters(), fastJsonConfig.getDateFormat(), JSON.DEFAULT_GENERATE_FEATURE, fastJsonConfig.getSerializerFeatures());
    			outputMessage.getBody().write(json.getBytes());
    			return;
    		} catch (Exception e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
        }
        super.writeInternal(obj, outputMessage);
    }

	public void setEnableEncrypt(boolean enableEncrypt) {
		this.enableEncrypt = enableEncrypt;
	}
	
}
