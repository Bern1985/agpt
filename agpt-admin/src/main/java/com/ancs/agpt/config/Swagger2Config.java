package com.ancs.agpt.config;
import com.fasterxml.classmate.TypeResolver;
import com.google.common.base.Predicate;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.google.common.collect.Lists.*;
import static springfox.documentation.schema.AlternateTypeRules.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.async.DeferredResult;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.schema.WildcardType;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Parameter;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.ApiKeyVehicle;
import springfox.documentation.swagger.web.SecurityConfiguration;
import springfox.documentation.swagger.web.UiConfiguration;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import springfox.documentation.service.ResponseMessage;  
import static springfox.documentation.builders.PathSelectors.*;
import static com.google.common.base.Predicates.*;
import static springfox.documentation.builders.RequestHandlerSelectors.*;
@Configuration
@EnableSwagger2
public class Swagger2Config{
	
	/* @Bean
	    public Docket createRestApi() {
	        return new Docket(DocumentationType.SWAGGER_2)
	                .apiInfo(apiInfo())
	                .select()
	                .apis(RequestHandlerSelectors.basePackage("com.ancs.agpt.rest.api"))
	                .paths(PathSelectors.any()) //过滤的接口
	                .build();
	    }*/
	
	@Autowired
	  private TypeResolver typeResolver;

	 @Bean
	  public Docket swaggerSpringMvcPlugin() {
	    return new Docket(DocumentationType.SWAGGER_2)
	    		.forCodeGeneration(true)
	    		.apiInfo(apiInfo())
	                            .select()
	               //Ignores controllers annotated with @CustomIgnore
	                            //.apis(RequestHandlerSelectors.basePackage("com.ancs.agpt.rest.api"))  
	              .apis(or(withClassAnnotation(Api.class),withMethodAnnotation(ApiOperation.class))) //Selection by RequestHandler
	              .paths(paths()) // and by paths
	              .build()
	              .directModelSubstitute(Date.class,String.class)
	              .genericModelSubstitutes(ResponseEntity.class)
	              .alternateTypeRules(
	                      newRule(typeResolver.resolve(DeferredResult.class,
	                              typeResolver.resolve(ResponseEntity.class, WildcardType.class)),
	                          typeResolver.resolve(WildcardType.class)))
	              .useDefaultResponseMessages(false)
	              .globalResponseMessage(RequestMethod.GET,customerResponseMessage())
	              .globalOperationParameters(setHeaderToken());
	           /* .securitySchemes(securitySchemes())
	            .securityContexts(securityContexts());*/
	  }
	 
	
	 
	

	 
	 private List<Parameter> setHeaderToken() {
	        ParameterBuilder tokenPar = new ParameterBuilder();
	        List<Parameter> pars = new ArrayList<>();
	        tokenPar.name("X-Auth-Token").description("token").modelRef(new ModelRef("string")).parameterType("header").required(true).build();
	        pars.add(tokenPar.build());
	        return pars;
	    }
	 
	  //Here is an example where we select any api that matches one of these paths
	  private Predicate<String> paths() {
	    return or(
	        regex("/api.*"),
	        regex("/api2.*"));
	       /* regex("/some.*"),
	        regex("/contacts.*"),
	        regex("/pet.*"),
	        regex("/springsRestController.*"),
	        regex("/test.*"));*/
	  }
	
	 
	 /** 
     * 自定义返回信息 
     * @param 
     * @return 
     */  
    private List<ResponseMessage> customerResponseMessage(){  
        return newArrayList(
                new ResponseMessageBuilder()//500  
                        .code(500)  
                        .message("500 message")  
                        .responseModel(new ModelRef("Error"))  
                        .build(),  
                new ResponseMessageBuilder()//401  
                        .code(401)  
                        .message("401 message")  
                        .build());  
    }  
	
	  
	    private ApiInfo apiInfo() {
	        return new ApiInfoBuilder()
	                .title("安管平台API") //大标题
	                .description("对外接口API的详情页面") //详细描述
	                .termsOfServiceUrl("NO terms of service")
	                .contact(new Contact("zhanghua", "http://www.ancs.com", "zhanghua@ancs.com"))
	                .version("1.0")
	                .build();
	    }
	    
	 

	   
	    
}
