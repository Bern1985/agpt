package com.ancs.agpt.config;

import java.util.List;

import javax.annotation.Resource;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

import org.apache.catalina.Context;
import org.apache.catalina.connector.Connector;
import org.apache.tomcat.util.descriptor.web.SecurityCollection;
import org.apache.tomcat.util.descriptor.web.SecurityConstraint;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import org.springframework.boot.context.embedded.EmbeddedServletContainerFactory;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import com.ancs.agpt.system.entity.RestUrl;
import com.ancs.agpt.system.service.RestUrlService;
import com.google.common.base.Joiner;

import static com.google.common.collect.Lists.*;

import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

/**
 * SpringBoot 启动完毕做些事情
 */
@Component
@Slf4j
public class ApplicationStartup  implements CommandLineRunner {
	
	
	@Resource
	private RestUrlService restUrlService;
	
	@Resource
	private RequestMappingHandlerMapping requestMappingHandlerMapping;
	
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		 /**
         * 初始化资源,保存到数据库
         */
        initModule();
	}
	
	 /**
     * 读取所有RestController包括以内的方法
     */
    private void initModule() {
    	java.util.Map<RequestMappingInfo, HandlerMethod> map = requestMappingHandlerMapping.getHandlerMethods();
    	List<RestUrl> restList = newLinkedList();
        for (RequestMappingInfo info : map.keySet()) {
        	Method method = map.get(info).getMethod();
        	ApiOperation apiOperation = method.getAnnotation(ApiOperation.class);
        	Annotation[][] pathannotations = method.getParameterAnnotations();
        	List<String> pathParam = newArrayList();
        	for (int i = 0; i < pathannotations.length; i++) {
        		Annotation[] pathannotationsArray = pathannotations[i];
        		for (int j = 0; j < pathannotationsArray.length; j++) {
        			Annotation pathannotation = pathannotationsArray[j];
        			if(pathannotation.annotationType().isAssignableFrom(PathVariable.class)) {
        				PathVariable pathVariable = (PathVariable) pathannotation;
        				String path = pathVariable.value();
        				pathParam.add(Joiner.on("").skipNulls().join("{",path,"}"));
        			}
				}
			}
        	if (apiOperation != null) {
        		 Class<?> aclResourceClass = map.get(info).getBeanType();
        		 RequestMapping moduleMapping = aclResourceClass.getAnnotation(RequestMapping.class);
        		 String httpmethod = Joiner.on(",").skipNulls().join(info.getMethodsCondition().getMethods());
        		 String url = Joiner.on(",").skipNulls().join(info.getPatternsCondition().getPatterns());
        		 if(pathParam.size()>0) {
        			 for(String p : pathParam) {
        				 url = url.replace(p, "*");
        			 }
        		}
        		 
        		 RestUrl restUrl = restUrlService.selectByUrlAndMethod(url, httpmethod);
        		 log.info("detect url = {} and method={} , then database has {}",url, httpmethod,restUrl);
        		 if(null == restUrl) {
        			 restUrl = new RestUrl();
            		 restUrl.setDescription(apiOperation.value());
            		 restUrl.setMethod(httpmethod);
            		 restUrl.setRestUrl(url);
            		 restList.add(restUrl);
        		 }
        	}
        }
        if(restList.size()>0) {
        	restUrlService.insertBatch(restList);
        }
    }
    
   
}
