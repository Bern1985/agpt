package com.ancs.agpt.security.access;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import org.springframework.security.access.SecurityMetadataSource;
import org.springframework.security.access.intercept.AbstractSecurityInterceptor;
import org.springframework.security.access.intercept.InterceptorStatusToken;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AncsFilterSecurityInterceptor extends AbstractSecurityInterceptor implements Filter {
	
	public AncsFilterSecurityInterceptor(FilterInvocationSecurityMetadataSource securityMetadataSource) {
		this.securityMetadataSource = securityMetadataSource;
	}
	
	public AncsFilterSecurityInterceptor() {
		
	}
	private FilterInvocationSecurityMetadataSource securityMetadataSource;

	/*public void setMyAccessDecisionManager(AncsAccessDecisionManager ancsAccessDecisionManager) {
		super.setAccessDecisionManager(ancsAccessDecisionManager);
	}*/

	@Override
	public Class<?> getSecureObjectClass() {
		// TODO Auto-generated method stub
		return FilterInvocation.class;
	}

	@Override
	public SecurityMetadataSource obtainSecurityMetadataSource() {
		// TODO Auto-generated method stub
		return this.securityMetadataSource;
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		if (log.isDebugEnabled()) {
			log.debug("doFilter(ServletRequest, ServletResponse, FilterChain) - start"); //$NON-NLS-1$
		}
		FilterInvocation fi = new FilterInvocation(request, response, chain);
        invoke(fi);
	}
	
	public void invoke(FilterInvocation fi) throws IOException, ServletException {
		//fi里面有一个被拦截的url
		//里面调用MyInvocationSecurityMetadataSource的getAttributes(Object object)这个方法获取fi对应的所有权限
		//再调用MyAccessDecisionManager的decide方法来校验用户的权限是否足够
		
		        InterceptorStatusToken token = super.beforeInvocation(fi);
		        try {
		//执行下一个拦截器
		            fi.getChain().doFilter(fi.getRequest(), fi.getResponse());
		        }
		        finally {
		            super.afterInvocation(token, null);
		        }
		    }
	
	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

}
