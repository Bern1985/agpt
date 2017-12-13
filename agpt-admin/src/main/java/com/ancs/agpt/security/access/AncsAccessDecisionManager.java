package com.ancs.agpt.security.access;

import java.text.MessageFormat;
import java.util.Collection;

import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

/**
 * 此类是决策器： 用来对 用户应有的角色,与URL地址可以访问的角色进行对比,如果不匹配则抛出异常
 * 
 */
@Slf4j
@Service
public class AncsAccessDecisionManager implements AccessDecisionManager {

	/**
	 * 决策方法： 如果方法执行完毕没有抛出异常,则说明可以放行, 否则抛出异常 AccessDeniedException authentication
	 * 是释CustomUserService中循环添加到 GrantedAuthority 对象中的权限信息集合. object
	 * 包含客户端发起的请求的requset信息，可转换为 HttpServletRequest request = ((FilterInvocation)
	 * object).getHttpRequest(); configAttributes
	 * 为MyInvocationSecurityMetadataSource的getAttributes(Object
	 * object)这个方法返回的结果，此方法是为了判定用户请求的url 是否在权限表中，如果在权限表中，则返回给 decide
	 * 方法，用来判定用户是否有此权限。如果不在权限表中则放行。
	 */
	@Override
	public void decide(Authentication authentication, Object object, Collection<ConfigAttribute> configAttributes)
			throws AccessDeniedException, InsufficientAuthenticationException {
		
		if (null == configAttributes || configAttributes.size() <= 0) {
			return;
		}
		Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
		ConfigAttribute attribute = configAttributes.stream().filter(configAttribute -> authorities.stream().anyMatch(ga -> configAttribute.getAttribute().equals(ga.getAuthority()))).findAny().orElse(null);
		if (log.isDebugEnabled()) {
			log.debug(MessageFormat.format("{0} has GrantedAuthority is - {1} , request is - {2}", authentication.getName(),authorities, object)); //$NON-NLS-1$
		}
		if(null != attribute) 
			return;
		
		// User Don't has needed authority that the resource ordered.
		throw new AccessDeniedException("Acces Denied.");
	}

	/**
	 * 返回true表示支持
	 */
	@Override
	public boolean supports(ConfigAttribute attribute) {
		// TODO Auto-generated method stub
		return true;
	}

	/**
	 * 返回true表示支持
	 */
	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return true;
	}

}
