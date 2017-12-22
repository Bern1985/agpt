package com.ancs.agpt.security.config;

import java.io.IOException;
import java.lang.reflect.Type;

import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.util.TypeUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import com.ancs.agpt.rest.model.RestResult;
import com.ancs.agpt.system.toolkit.AESUtil;
import com.fasterxml.jackson.core.JsonEncoding;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationConfig;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.ser.FilterProvider;

import springfox.documentation.spring.web.json.Json;

public class AncsHttpMessageConverter extends MappingJackson2HttpMessageConverter{
	private static final MediaType TEXT_EVENT_STREAM = new MediaType("text", "event-stream");
	
	public AncsHttpMessageConverter() {
        super();
    }
	private boolean enableEncrypt;
	
	
	@Override
	protected void writeInternal(Object object, Type type, HttpOutputMessage outputMessage)
			throws IOException, HttpMessageNotWritableException {
		// TODO Auto-generated method stub
		 if(enableEncrypt && object.getClass().isAssignableFrom(RestResult.class)) {
	            try {
	            	RestResult result = (RestResult) object;
	            	ObjectMapper mapper = getObjectMapper();
	            	String jsonContent = mapper.writeValueAsString(result.getContent());
	            	String jsonEncrypt = AESUtil.encrypt(jsonContent);
	            	result.setContent(jsonEncrypt);
	            	String json = mapper.writeValueAsString(result);
	    			outputMessage.getBody().write(json.getBytes());
	    			return;
	    		} catch (Exception e) {
	    			// TODO Auto-generated catch block
	    			e.printStackTrace();
	    		}
	        }
		 superWriteInternal(object,type,outputMessage);
	}
	
	void superWriteInternal(Object object, Type type, HttpOutputMessage outputMessage)
			throws IOException, HttpMessageNotWritableException {
		MediaType contentType = outputMessage.getHeaders().getContentType();
		JsonEncoding encoding = getJsonEncoding(contentType);
		JsonGenerator generator = this.objectMapper.getFactory().createGenerator(outputMessage.getBody(), encoding);
		try {
			writePrefix(generator, object);

			Class<?> serializationView = null;
			FilterProvider filters = null;
			Object value = object;
			JavaType javaType = null;
			if (object instanceof MappingJacksonValue) {
				MappingJacksonValue container = (MappingJacksonValue) object;
				value = container.getValue();
				serializationView = container.getSerializationView();
				filters = container.getFilters();
			}
			if (type != null && value != null && TypeUtils.isAssignable(type, value.getClass())) {
				javaType = getJavaType(type, null);
			}
			ObjectWriter objectWriter;
			if (serializationView != null) {
				objectWriter = this.objectMapper.writerWithView(serializationView);
			}
			else if (filters != null) {
				objectWriter = this.objectMapper.writer(filters);
			}
			else {
				objectWriter = this.objectMapper.writer();
			}
			if (javaType != null && javaType.isContainerType()) {
				objectWriter = objectWriter.forType(javaType);
			}
			SerializationConfig config = objectWriter.getConfig();
			if (contentType != null && contentType.isCompatibleWith(TEXT_EVENT_STREAM) &&
					config.isEnabled(SerializationFeature.INDENT_OUTPUT)) {
				objectWriter = objectWriter.with(new DefaultPrettyPrinter());
			}
			objectWriter.writeValue(generator, value);

			writeSuffix(generator, object);
			generator.flush();

		}
		catch (JsonProcessingException ex) {
			throw new HttpMessageNotWritableException("Could not write JSON: " + ex.getOriginalMessage(), ex);
		}
	}
	
	/*@Override
    protected void writeInternal(Object obj, HttpOutputMessage outputMessage)
            throws IOException, HttpMessageNotWritableException {
        if(enableEncrypt && obj.getClass().isAssignableFrom(RestResult.class)) {
            try {
            	  ObjectMapper mapper = getObjectMapper();
            	  RestResult result = (RestResult) obj;
            	  String jsonContent = mapper.writeValueAsString(result.getContent());
            	  String jsonEncrypt = AESUtil.encrypt(jsonContent);
              		result.setContent(jsonEncrypt);
                  String json = mapper.writeValueAsString(result);
                  outputMessage.getBody().write(json.getBytes());
    		} catch (Exception e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
        }
        super.writeInternal(obj, outputMessage);
    }*/

	public void setEnableEncrypt(boolean enableEncrypt) {
		this.enableEncrypt = enableEncrypt;
	}
	
}
