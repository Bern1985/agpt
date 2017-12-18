package com.ancs.agpt.security.config;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.security.Key;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;

import com.ancs.agpt.cons.Constants;



public class JwtTokenUtils {
		
	    private final static String secret = "my-very-secret-key";
	    
	    public static String getUsernameFromToken(String token) {
	        String username;
	        try {
	            final Claims claims = getClaimsFromToken(token);
	            username = claims.getSubject();
	        } catch (Exception e) {
	            username = null;
	        }
	        return username;
	    }
	    
	    /*public static Date getExpirationFromToken(String token) {
	        Date exp;
	        try {
	            final Claims claims = getClaimsFromToken(token);
	            exp = claims.getExpiration();
	        } catch (Exception e) {
	        	exp = null;
	        }
	        return exp;
	    }*/

	    public static Claims getClaimsFromToken(String token) {
	        Claims claims;
	        try {
	            claims = Jwts.parser()
	            		.setSigningKey(DatatypeConverter.parseBase64Binary(secret))
	                    .parseClaimsJws(token)
	                    .getBody();
	        } catch (Exception e) {
	            claims = null;
	        }
	        return claims;
	    }
	    
	    public static String generateToken(String username) {
	    	SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
	    	//生成签名密钥 就是一个base64加密后的字符串？
	    	byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary (secret); 
	    	Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm. getJcaName());
	    	 String token = Jwts.builder()
	                 .setSubject(username)
	                 .signWith(signatureAlgorithm, signingKey) //采用什么算法是可以自己选择的，不一定非要采用HS512
	                 .compact();
	    	 return token;
	    }
	    
	    public static void main(String[] args) {
	    	String token = generateToken(Constants.SUPER_ADMIN);
	    	System.out.println(token);
//			String token = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbiIsImV4cCI6MTUxMjIwMDAyNSwibmJmIjoxNTEyMTEzNjI1fQ.SouHPYpxrf4YgTxYBn6352WxCLt_0kixg1Q0IyEqfl9x8DmU6tospgf_auGfP3Ai7hTip5aB3PCtXMo4dHIR7g";
			System.out.println(getUsernameFromToken(token));
		}
}
