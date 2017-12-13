package com.ancs.agpt.security.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ancs.agpt.security.model.AuthenticatedUser;
import com.ancs.agpt.system.entity.DomainRole;
import com.ancs.agpt.system.entity.RestUrl;
import com.ancs.agpt.system.mapper.DomainMapper;
import com.ancs.agpt.system.mapper.DomainRoleMapper;
import com.ancs.agpt.system.mapper.RestUrlMapper;
import com.google.common.base.Joiner;

@Service
public class SecurityUserService  implements UserDetailsService { //自定义UserDetailsService 接口
	
	@Autowired
    DomainMapper domainMapper;
	
	@Autowired
    RestUrlMapper restUrlMapper;
	
	@Transactional(readOnly = true, propagation=Propagation.SUPPORTS)
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		com.ancs.agpt.system.entity.Domain domain = domainMapper.findByDomainName(username);
		 if (domain != null) {
			 	List<RestUrl> restUrlList = restUrlMapper.findByDomainId(domain.getId());
	            List<GrantedAuthority> grantedAuthorities = restUrlList.stream().filter(permission -> {return (permission != null && permission.getRestUrl()!=null && permission.getMethod()!=null);}).map(permission -> {return new SimpleGrantedAuthority(Joiner.on("").join(permission.getMethod(),":",permission.getRestUrl())); })
	            .collect(Collectors.toList());
	            return new AuthenticatedUser(domain, grantedAuthorities);
	        } else {
	            throw new UsernameNotFoundException("admin: " + username + " do not exist!");
	        }
	    }

}
