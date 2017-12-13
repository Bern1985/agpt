package com.ancs.agpt.security.exception;

import org.springframework.security.core.AuthenticationException;

public class JwtTokenMalformedException extends AuthenticationException {


    /**
	 * 
	 */
	private static final long serialVersionUID = 8619827701244199215L;

	public JwtTokenMalformedException(String msg) {
        super(msg);
    }
}

