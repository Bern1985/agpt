package com.ancs.agpt.security.config;

import java.io.IOException;
import java.text.MessageFormat;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.expression.ParseException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.util.Assert;

import com.ancs.agpt.security.exception.JwtTokenMissingException;
import com.ancs.agpt.security.model.JwtAuthenticationToken;
import com.ancs.agpt.system.toolkit.StringUtils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class JwtAuthenticationTokenFilter extends AbstractAuthenticationProcessingFilter {

	public final String HEADER_SECURITY_TOKEN = "X-Auth-Token";

	public JwtAuthenticationTokenFilter(String url, AuthenticationManager authManager) {
		// super("/api/**");
		super(new AntPathRequestMatcher(url));
		setAuthenticationManager(authManager);

	}

	@Override
	protected boolean requiresAuthentication(HttpServletRequest request, HttpServletResponse response) {
		return true;
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {

		String token = request.getHeader(HEADER_SECURITY_TOKEN);
		log.info("token found:" + token);
		if (StringUtils.isEmpty(token)) {
			throw new JwtTokenMissingException("No JWT token found in request headers");
		}
		String username = JwtTokenUtils.getUsernameFromToken(token);
		log.info("checking authentication " + username);
		Assert.notNull(username, MessageFormat.format("Error | {0} do not exist!", username));
		JwtAuthenticationToken tokenInfo = new JwtAuthenticationToken(token);

		Authentication userAuthenticationToken;
		try {
			userAuthenticationToken = getAuthenticationManager().authenticate(tokenInfo);
			if (userAuthenticationToken == null)
				throw new AuthenticationServiceException(MessageFormat.format("Error | {0}", "Bad Token"));
			logger.info("authenticated user " + username + ", setting security context");
			SecurityContextHolder.getContext().setAuthentication(userAuthenticationToken);
			return userAuthenticationToken;
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

	}

	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {
		super.successfulAuthentication(request, response, chain, authResult);

		// As this authentication is in HTTP header, after success we need to continue
		// the request normally
		// and return the response as if the resource was not secured at all
		chain.doFilter(request, response);
	}
}
