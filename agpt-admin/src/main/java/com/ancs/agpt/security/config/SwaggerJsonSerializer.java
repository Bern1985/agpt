package com.ancs.agpt.security.config;

import java.io.IOException;
import java.lang.reflect.Type;

import com.alibaba.fastjson.parser.DefaultJSONParser;
import com.alibaba.fastjson.parser.deserializer.ObjectDeserializer;
import com.alibaba.fastjson.serializer.JSONSerializer;
import com.alibaba.fastjson.serializer.ObjectSerializer;
import com.alibaba.fastjson.serializer.SerializeWriter;

import springfox.documentation.spring.web.json.Json;

public class SwaggerJsonSerializer implements ObjectSerializer, ObjectDeserializer {
	 public final static SwaggerJsonSerializer instance = new SwaggerJsonSerializer();

	@Override
	public <T> T deserialze(DefaultJSONParser parser, Type type, Object fieldName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getFastMatchToken() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void write(JSONSerializer serializer, Object object, Object fieldName, Type fieldType, int features)
			throws IOException {
		// TODO Auto-generated method stub
		 SerializeWriter out = serializer.getWriter();
	        Json json = (Json) object;
	        out.write(json.value());
	}

}
