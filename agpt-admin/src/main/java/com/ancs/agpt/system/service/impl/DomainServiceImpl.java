package com.ancs.agpt.system.service.impl;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ancs.agpt.system.entity.Domain;
import com.ancs.agpt.system.mapper.DomainMapper;
import com.ancs.agpt.system.service.DomainService;
@Service
public class DomainServiceImpl extends BaseServiceImpl<DomainMapper,Domain> implements DomainService{

	@Transactional(propagation=Propagation.REQUIRED,isolation = Isolation.READ_COMMITTED,rollbackFor = Exception.class,timeout=60)
	public boolean deleteById(Long id) {
		// TODO Auto-generated method stub
		Domain domain = new Domain();
		domain.setId(id);
		domain.setTs(LocalDateTime.now());
		domain.setDr(1);
    	return retBool(baseMapper.update(domain));
	}

	@Override
	public Domain get(Object param) {
		// TODO Auto-generated method stub
		return null;
	}

}
