package com.ancs.agpt.security.access;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Service;

import com.ancs.agpt.mybatis.plugin.Page;
import com.ancs.agpt.system.entity.RestUrl;
import com.ancs.agpt.system.service.RestUrlService;
import com.google.common.base.Joiner;
import com.google.common.base.Splitter;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Service
@Slf4j
public class AncsInvocationSecurityMetadataSourceService implements FilterInvocationSecurityMetadataSource {
	
	@Autowired
	private RestUrlService restUrlService;
	
	private Map<String, Collection<ConfigAttribute>> resourceMap = null;

	/**
     * 加载权限表中所有权限
     */
    public void loadResourceDefine(){
    	resourceMap = new HashMap<>();
        Page<RestUrl> page = restUrlService.selectPage(null);
        page.getRecords().stream().forEach(restUrl -> {
        	 Collection<ConfigAttribute> array = new ArrayList<>();
             String permission = Joiner.on("").join(restUrl.getMethod(),":",restUrl.getRestUrl());
             ConfigAttribute cfg = new SecurityConfig(Joiner.on("").join(restUrl.getMethod(),":",restUrl.getRestUrl()));
             array.add(cfg);
             //用权限的getUrl() 作为map的key，用ConfigAttribute的集合作为 value，
             resourceMap.put(permission, array);
        });;
       

    }

    
	//此方法是为了判定用户请求的url 是否在权限表中，如果在权限表中，则返回给 decide 方法，用来判定用户是否有此权限。如果不在权限表中则放行。
	public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		if(resourceMap ==null) loadResourceDefine();
		 //object 中包含用户请求的request 信息
        HttpServletRequest request = ((FilterInvocation) object).getHttpRequest();
        String method = request.getMethod();
        if (log.isDebugEnabled()) {
        	log.debug("getAttributes(Object) - String requestUrl= " + request.getRequestURL()); //$NON-NLS-1$
		}
        return resourceMap.keySet().stream().filter(permission -> {
        		List<String> config = Splitter.on(":").splitToList(permission);
        		return config.get(0).equals(method) && new AntPathRequestMatcher(config.get(1)).matches(request);
        	}).findFirst().map(m ->resourceMap.get(m)).orElse(null);
	}

	@Override
	public Collection<ConfigAttribute> getAllConfigAttributes() {
		// TODO Auto-generated method stub
		return new ArrayList();
	}

	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return true;
	}
	
}
