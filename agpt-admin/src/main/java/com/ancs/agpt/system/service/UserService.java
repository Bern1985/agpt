package com.ancs.agpt.system.service;


import com.ancs.agpt.system.entity.User;

public interface UserService extends BaseService<User> {
	boolean updateLastLoginTime(Long id);
	
}
