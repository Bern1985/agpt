package com.ancs.agpt.system.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ancs.agpt.system.entity.DomainRoleRel;
import com.ancs.agpt.system.mapper.DomainRoleRelMapper;
import com.ancs.agpt.system.service.DomainRoleRelService;
import com.ancs.agpt.system.toolkit.SqlHelper;
@Service
public class DomainRoleRelServiceImpl implements DomainRoleRelService{
	@Autowired
	private DomainRoleRelMapper domainRoleRelMapper;
	
	@Transactional(propagation=Propagation.REQUIRED,isolation = Isolation.READ_COMMITTED,rollbackFor = Exception.class,timeout=60)
	public boolean deleteById(Long id) {
		// TODO Auto-generated method stub
    	return retBool(domainRoleRelMapper.deleteById(id));
	}

	private static boolean retBool(Integer result) {
        return SqlHelper.retBool(result);
    }
	
	@Transactional(propagation=Propagation.REQUIRED,isolation = Isolation.READ_COMMITTED,rollbackFor = Exception.class,timeout=60)
	public boolean insert(DomainRoleRel entity) {
		// TODO Auto-generated method stub
		return retBool(domainRoleRelMapper.insert(entity));
	}

	@Transactional(propagation=Propagation.REQUIRED,isolation = Isolation.READ_COMMITTED,rollbackFor = Exception.class,timeout=60)
	public boolean update(DomainRoleRel entity) {
		// TODO Auto-generated method stub
		return retBool(domainRoleRelMapper.update(entity));
	}
   
}