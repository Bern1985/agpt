package com.ancs.agpt.config;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.ancs.agpt.security.config.AncsHttpMessageConverter;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.ser.DefaultSerializerProvider;
import com.fasterxml.jackson.databind.ser.SerializerFactory;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;



@Configuration
public class ResourceConfig extends WebMvcConfigurerAdapter {
	
	@Value("${agpt.config.enableEncrypt}")
	private boolean enableEncrypt;
	
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		
		registry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");

		registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
		registry.addResourceHandler("/**").addResourceLocations("classpath:/static/");
		super.addResourceHandlers(registry);
	}
	
	@Override
	public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
		// TODO Auto-generated method stub
		super.configureMessageConverters(converters);
		AncsHttpMessageConverter fastConverter = new AncsHttpMessageConverter();  
		   
        FastJsonConfig fastJsonConfig = new FastJsonConfig();  
        fastJsonConfig.setSerializerFeatures(  
                SerializerFeature.PrettyFormat  
        );  
        fastConverter.setFastJsonConfig(fastJsonConfig);  
        fastConverter.setEnableEncrypt(enableEncrypt);
        converters.add(fastConverter);  
	}
	

	/**
	 * 自定义JSON输入输出格式
	 *
	 * @return
	 */
	@Bean
	public MappingJackson2HttpMessageConverter customJackson2HttpMessageConverter() {
		MappingJackson2HttpMessageConverter jsonConverter = new MappingJackson2HttpMessageConverter();
		ObjectMapper om = new ObjectMapper();
		om.configure(MapperFeature.DEFAULT_VIEW_INCLUSION, false);// 开启将输出没有JsonView注解的属性，false关闭将输出有JsonView注解的属性
		om.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);// 配置该objectMapper在反序列化时，忽略目标对象没有的属性。凡是使用该objectMapper反序列化时，都会拥有该特性。
		om.setSerializerProvider(new CustomNullStringSerializerProvider());
		jsonConverter.setObjectMapper(om);
		return jsonConverter;
	}
	
	/**
	 * 扩展输出
	 */
	@Override
	public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
		converters.add(customJackson2HttpMessageConverter());
	}

	/**
	 * 自定义String序列化工具，使null转成""输出。
	 *
	 * @author Frank.Zeng
	 *
	 */
	public static class CustomNullStringSerializerProvider extends DefaultSerializerProvider {
		private static final long serialVersionUID = 1L;

		public CustomNullStringSerializerProvider() {
			super();
		}

		public CustomNullStringSerializerProvider(CustomNullStringSerializerProvider provider,
				SerializationConfig config, SerializerFactory jsf) {
			super(provider, config, jsf);
		}

		@Override
		public CustomNullStringSerializerProvider createInstance(SerializationConfig config, SerializerFactory jsf) {
			return new CustomNullStringSerializerProvider(this, config, jsf);
		}

		// 这是最关键的部分，用于处理仅对String类型数据生效的null。
		@Override
		public JsonSerializer<Object> findNullValueSerializer(BeanProperty property) throws JsonMappingException {
			if (property.getType().getRawClass().equals(String.class)
					|| property.getType().getRawClass().equals(Date.class)) {
				return EmptyStringSerializer.INSTANCE;
			} else if (property.getType().getRawClass().equals(Integer.class)) {
				return EmptyIntegerSerializer.INSTANCE;
			} else if (property.getType().getRawClass().equals(Double.class)) {
				return EmptyDoubleSerializer.INSTANCE;
			} else if (property.getType().getRawClass().equals(Boolean.class)) {
				return EmptyBooleanSerializer.INSTANCE;
			} else if (property.getType().getRawClass().equals(List.class)
					|| property.getType().getRawClass().equals(Set.class)) {
				return EmptyArraySerializer.INSTANCE;
			} else {
				return super.findNullValueSerializer(property);
			}
		}
	}

	// 自定义序列化
	public static class EmptyStringSerializer extends JsonSerializer<Object> {
		public static final JsonSerializer<Object> INSTANCE = new EmptyStringSerializer();

		private EmptyStringSerializer() {
		}

		@Override
		public void serialize(Object o, JsonGenerator jsonGenerator, SerializerProvider serializerProvider)
				throws IOException, JsonProcessingException {
			jsonGenerator.writeString("");
		}
	}

	// 自定义序列化
	public static class EmptyIntegerSerializer extends JsonSerializer<Object> {
		public static final JsonSerializer<Object> INSTANCE = new EmptyIntegerSerializer();

		private EmptyIntegerSerializer() {
		}

		@Override
		public void serialize(Object o, JsonGenerator jsonGenerator, SerializerProvider serializerProvider)
				throws IOException, JsonProcessingException {
			jsonGenerator.writeNumber(0);
		}
	}

	// 自定义序列化
	public static class EmptyDoubleSerializer extends JsonSerializer<Object> {
		public static final JsonSerializer<Object> INSTANCE = new EmptyDoubleSerializer();

		private EmptyDoubleSerializer() {
		}

		@Override
		public void serialize(Object o, JsonGenerator jsonGenerator, SerializerProvider serializerProvider)
				throws IOException, JsonProcessingException {
			jsonGenerator.writeNumber(0d);
		}
	}

	// 自定义序列化
	public static class EmptyBooleanSerializer extends JsonSerializer<Object> {
		public static final JsonSerializer<Object> INSTANCE = new EmptyBooleanSerializer();

		private EmptyBooleanSerializer() {
		}

		@Override
		public void serialize(Object o, JsonGenerator jsonGenerator, SerializerProvider serializerProvider)
				throws IOException, JsonProcessingException {
			jsonGenerator.writeBoolean(false);
		}
	}

	// 自定义序列化
	public static class EmptyArraySerializer extends JsonSerializer<Object> {
		public static final JsonSerializer<Object> INSTANCE = new EmptyArraySerializer();

		private EmptyArraySerializer() {
		}

		@Override
		public void serialize(Object o, JsonGenerator jsonGenerator, SerializerProvider serializerProvider)
				throws IOException, JsonProcessingException {
			int[] a = {};
			jsonGenerator.writeArray(a, 0, 0);
		}
	}
	
	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**")
		.allowedOrigins("*")
		.allowCredentials(true)
		.allowedMethods("GET", "POST", "DELETE", "PUT")
		.maxAge(3600);
	}
	private CorsConfiguration buildConfig() {
		CorsConfiguration corsConfiguration = new CorsConfiguration();
		List<String> list = new ArrayList<>();
		list.add("*");
		corsConfiguration.setAllowedOrigins(list);
		corsConfiguration.addAllowedOrigin("*"); // 1
		corsConfiguration.addAllowedHeader("*"); // 2
		corsConfiguration.addAllowedMethod("*"); // 3
		return corsConfiguration;
	}
	@Bean
	public CorsFilter corsFilter() {
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource(); 
		source.registerCorsConfiguration("/**", buildConfig()); // 4
		return new CorsFilter(source);
	}
}
