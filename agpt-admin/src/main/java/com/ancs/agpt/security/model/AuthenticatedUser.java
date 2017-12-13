package com.ancs.agpt.security.model;
import com.ancs.agpt.system.entity.Domain;
import com.ancs.agpt.system.entity.enums.Status;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Date;

public class AuthenticatedUser  implements UserDetails {
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 8994213830338755740L;
	
	private Domain domain;
	
    private final Collection<? extends GrantedAuthority> authorities;

    public AuthenticatedUser(Domain domain, Collection<? extends GrantedAuthority> authorities) {
        this.domain = domain;
        this.authorities = authorities;
    }

    

    @Override
    public String getUsername() {
        return domain.getAccount();
    }

    @Override
    @JsonIgnore
    public boolean isAccountNonExpired() {
    	long nowMillis =System.currentTimeMillis();
		Date now = new Date(nowMillis);
		if(domain.getTtl()==0) {
			Date exp = new Date(domain.getCreatetime().getTime() +domain.getTtl());
	        return (exp.getTime()-now.getTime())>0;
		}
		return true;
    }

    @Override
    @JsonIgnore
    public boolean isAccountNonLocked() {
        return !domain.getStatus().equals(Status.LOCKED);
    }

    @Override
    @JsonIgnore
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    @JsonIgnore
    public boolean isEnabled() {
        return true;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return domain.getSecret();
	}
    
}
