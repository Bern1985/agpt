package com.ancs.agpt.system.service.impl;

import java.time.Instant;
import java.util.Date;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ancs.agpt.system.entity.User;
import com.ancs.agpt.system.mapper.UserMapper;
import com.ancs.agpt.system.service.UserService;

@Service
public class UserServiceImpl extends BaseServiceImpl<UserMapper,User> implements UserService {
	
	@Override
	public User get(Object param) {
		// TODO Auto-generated method stub
		return null;
	}

	@Transactional(propagation=Propagation.REQUIRED,isolation = Isolation.READ_COMMITTED,rollbackFor = Exception.class,timeout=60)
	public boolean updateLastLoginTime(Long id) {
		// TODO Auto-generated method stub
		User user = new User();
		user.setId(id);
		user.setLastLoginTime(Date.from(Instant.now()));
		return retBool(baseMapper.update(user));
	}
	
	/*@Transactional(propagation=Propagation.REQUIRED,isolation = Isolation.READ_COMMITTED,rollbackFor = Exception.class,timeout=60)
	public boolean deleteById(Long id) {
		// TODO Auto-generated method stub
    	User user = new User();
    	user.setId(id);
		user.setTs(LocalDateTime.now());
		user.setDr(1);
    	return retBool(baseMapper.update(user));
	}*/
}
