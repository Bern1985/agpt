package com.ancs.agpt.system.service.impl;

import java.time.Instant;
import java.util.Date;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ancs.agpt.system.entity.DomainRole;
import com.ancs.agpt.system.mapper.DomainRoleMapper;
import com.ancs.agpt.system.service.DomainRoleService;
import com.ancs.agpt.system.toolkit.SqlHelper;
@Service
public class DomainRoleServiceImpl extends BaseServiceImpl<DomainRoleMapper,DomainRole> implements DomainRoleService{

	@Transactional(propagation=Propagation.REQUIRED,isolation = Isolation.READ_COMMITTED,rollbackFor = Exception.class,timeout=60)
	public boolean deleteById(Long id) {
		// TODO Auto-generated method stub
		DomainRole domainRole = new DomainRole();
		domainRole.setId(id);
		domainRole.setTs(Date.from(Instant.now()));
		domainRole.setDr(1);
    	return retBool(baseMapper.update(domainRole));
	}
	
	
	@Override
	public DomainRole get(Object param) {
		// TODO Auto-generated method stub
		return null;
	}

}