package com.ancs.agpt.security.config;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class JwtTokenGenerator {

    /**
     * Generates a JWT token containing username as subject, and userId and role as additional claims. These properties are taken from the specified
     * User object. Tokens validity is infinite.
     *
     * @param u the user for which the token will be generated
     * @return the JWT token
     */
    /*public static String generateToken(JwtUserDto u, String secret,Date expiration) {
        Claims claims = Jwts.claims().setSubject(u.getUsername());
        claims.put("userId", u.getId() + "");
        claims.put("role", u.getRole());
        
        return Jwts.builder()
        		.setExpiration(expiration)
                .setClaims(claims)
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }*/

    public static String generateToken(String username,String secret) {
    	long nowMillis =System.currentTimeMillis();
		Date now = new Date(nowMillis);
    	 String token = Jwts.builder()
                 .setSubject(username)
                 .signWith(SignatureAlgorithm.HS512, secret) //采用什么算法是可以自己选择的，不一定非要采用HS512
                 .compact();
    	 return token;
    }
    
   /* public static String getUsernameFromToken(String token) {
        String username;
        try {
            final Claims claims = getClaimsFromToken(token,"my-very-secret-key");
            username = claims.getSubject();
        } catch (Exception e) {
            username = null;
        }
        return username;
    }
    
    private static Claims getClaimsFromToken(String token,String secret) {
        Claims claims;
        try {
            claims = Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token)
                    .getBody();
        } catch (Exception e) {
            claims = null;
        }
        return claims;
    }*/
    
    /**
     * @param args
     */
    public static void main(String[] args) {
        String endtime = "2017-11-16 00:00:00";
        DateTimeFormatter sf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime endLocalDate = LocalDateTime.parse(endtime, sf);
        //String token = generateToken(user, "my-very-secret-key",Date.from(endLocalDate.atZone(ZoneId.systemDefault()).toInstant()));
        String token = generateToken("oa123","my-very-secret-key");
        System.out.println("**************************************\n\n" + token + "\n\n**************************************");
       // System.out.println("**************************************\n\n" + getUsernameFromToken(token) + "\n\n**************************************");
    }

}
