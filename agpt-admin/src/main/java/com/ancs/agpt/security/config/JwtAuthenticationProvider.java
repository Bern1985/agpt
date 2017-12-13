package com.ancs.agpt.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;



import com.ancs.agpt.security.exception.JwtTokenMalformedException;
import com.ancs.agpt.security.model.JwtAuthenticationToken;

@Component
public class JwtAuthenticationProvider  extends AbstractUserDetailsAuthenticationProvider {
	
	  	@Autowired
		private UserDetailsService userDetailsService;

	    @Override
	    public boolean supports(Class<?> authentication) {
	        return (JwtAuthenticationToken.class.isAssignableFrom(authentication));
	    }

	    @Override
	    protected void additionalAuthenticationChecks(UserDetails userDetails, UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
	    }

	    @Override
	    protected UserDetails retrieveUser(String username, UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
	        JwtAuthenticationToken jwtAuthenticationToken = (JwtAuthenticationToken) authentication;
	        String token = jwtAuthenticationToken.getToken();
	        String account = JwtTokenUtils.getUsernameFromToken(token);
	        UserDetails userDetails = userDetailsService.loadUserByUsername(account);
	        if (userDetails == null) {
	            throw new JwtTokenMalformedException("JWT token is not valid");
	        }
	        return userDetails;
	     
	    }


}
