package com.ancs.agpt.security.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ancs.agpt.cons.Constants;
import com.ancs.agpt.mybatis.plugin.Page;
import com.ancs.agpt.security.model.AuthenticatedUser;
import com.ancs.agpt.system.entity.RestUrl;
import com.ancs.agpt.system.mapper.DomainMapper;
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
		List<RestUrl> restUrlList = null;
		 List<GrantedAuthority> grantedAuthorities = null;
		 if(Constants.SUPER_ADMIN.equals(username)) {
	        	UserBuilder users = User.withUsername(Constants.SUPER_ADMIN);
	        	 Page<RestUrl> page = new Page<RestUrl>(1,Integer.MAX_VALUE);
	        	 page.setRecords(restUrlMapper.selectPage(page));
	        	 restUrlList = page.getRecords();
	        	 grantedAuthorities = restUrlList.stream().filter(permission -> {return (permission != null && permission.getRestUrl()!=null && permission.getMethod()!=null);}).map(permission -> {return new SimpleGrantedAuthority(Joiner.on("").join(permission.getMethod(),":",permission.getRestUrl())); })
	     	            .collect(Collectors.toList());
	        	return users.password("zhanghua123456").roles(Constants.ROLE_SUPER_ADMIN).accountExpired(false).accountExpired(false).accountLocked(false).authorities(grantedAuthorities).build();
	        	
		 }
		 
		com.ancs.agpt.system.entity.Domain domain = domainMapper.findByDomainName(username);
		 if (domain != null) {
			 	restUrlList = restUrlMapper.findByDomainId(domain.getId());
	            grantedAuthorities = restUrlList.stream().filter(permission -> {return (permission != null && permission.getRestUrl()!=null && permission.getMethod()!=null);}).map(permission -> {return new SimpleGrantedAuthority(Joiner.on("").join(permission.getMethod(),":",permission.getRestUrl())); })
	            .collect(Collectors.toList());
	            return new AuthenticatedUser(domain, grantedAuthorities);
	        } else {
	            throw new UsernameNotFoundException("admin: " + username + " do not exist!");
	        }
	    }
	
	
}
