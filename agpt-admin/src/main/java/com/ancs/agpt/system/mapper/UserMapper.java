package com.ancs.agpt.system.mapper;

import com.ancs.agpt.system.entity.User;

public interface UserMapper extends BaseMapper<User> {
	User findByUserName(String username);
}
