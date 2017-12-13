package com.ancs.agpt.security.model;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

public class JwtAuthenticationToken extends UsernamePasswordAuthenticationToken {
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 8641835725500768065L;
	private String token;

    public JwtAuthenticationToken(String token) {
        super(null, null);
        this.token = token;
    }

    public String getToken() {
        return token;
    }
    //password
    @Override
    public Object getCredentials() {
        return null;
    }
    //username
    @Override
    public Object getPrincipal() {
        return null;
    }

    
}
