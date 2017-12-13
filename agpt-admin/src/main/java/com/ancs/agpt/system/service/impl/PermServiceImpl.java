package com.ancs.agpt.system.service.impl;

import java.time.Instant;
import java.util.Date;
import java.util.List;

import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ancs.agpt.system.entity.Perm;
import com.ancs.agpt.system.mapper.PermMapper;
import com.ancs.agpt.system.service.PermService;

public class PermServiceImpl extends BaseServiceImpl<PermMapper,Perm> implements PermService {
	@Transactional(propagation=Propagation.REQUIRED,isolation = Isolation.READ_COMMITTED,rollbackFor = Exception.class,timeout=60)
	public boolean deleteById(Long id) {
		// TODO Auto-generated method stub
		Perm perm = new Perm();
		perm.setId(id);
		perm.setTs(Date.from(Instant.now()));
		perm.setDr(1);
    	return retBool(baseMapper.update(perm));
	}

	@Override
	public Perm get(Object param) {
		// TODO Auto-generated method stub
		return null;
	}

	@Transactional(readOnly = true, propagation=Propagation.SUPPORTS)
	public List<Perm> findByUserId(Long userId) {
		// TODO Auto-generated method stub
		return baseMapper.findByUserId(userId);
	}
}
