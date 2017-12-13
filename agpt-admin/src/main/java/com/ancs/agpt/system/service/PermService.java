package com.ancs.agpt.system.service;

import java.util.List;

import com.ancs.agpt.system.entity.Perm;

public interface PermService extends BaseService<Perm> {
	List<Perm>  findByUserId(Long userId);
}
