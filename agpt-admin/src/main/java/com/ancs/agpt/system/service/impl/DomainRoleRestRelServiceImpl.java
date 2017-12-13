package com.ancs.agpt.system.service.impl;

import static com.google.common.collect.Lists.newArrayList;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ancs.agpt.system.entity.DomainRoleRestRel;
import com.ancs.agpt.system.mapper.DomainRoleRestRelMapper;
import com.ancs.agpt.system.service.DomainRoleRestRelService;
import com.ancs.agpt.system.toolkit.SqlHelper;
import com.google.common.collect.Lists;

@Service
public class DomainRoleRestRelServiceImpl implements DomainRoleRestRelService{
	@Autowired
	private DomainRoleRestRelMapper domainRoleRestRelMapper;
	
	@Transactional(propagation=Propagation.REQUIRED,isolation = Isolation.READ_COMMITTED,rollbackFor = Exception.class,timeout=60)
	public boolean insert(DomainRoleRestRel entity) {
		// TODO Auto-generated method stub
		return retBool(domainRoleRestRelMapper.insert(entity));
	}
	
	@Transactional(propagation=Propagation.REQUIRED,isolation = Isolation.READ_COMMITTED,rollbackFor = Exception.class,timeout=60)
	public void insertBatch(Collection<DomainRoleRestRel> entityArray) {
		// TODO Auto-generated method stub
		List<DomainRoleRestRel> data = newArrayList(entityArray);
		int batchSize = 500;//每次提交500条
		int loop = (int) Math.ceil(data.size() / (double) batchSize);
		List<List<DomainRoleRestRel>> subList=Lists.partition(data,loop);  
		subList.stream().forEach(list -> domainRoleRestRelMapper.insertBatch(list));
	}

	@Transactional(propagation=Propagation.REQUIRED,isolation = Isolation.READ_COMMITTED,rollbackFor = Exception.class,timeout=60)
	public boolean deleteById(Long id) {
		// TODO Auto-generated method stub
		return retBool(domainRoleRestRelMapper.deleteById(id));
	}

	@Transactional(propagation=Propagation.REQUIRED,isolation = Isolation.READ_COMMITTED,rollbackFor = Exception.class,timeout=60)
	public boolean update(DomainRoleRestRel entity) {
		// TODO Auto-generated method stub
		return retBool(domainRoleRestRelMapper.update(entity));
	}
	
	private static boolean retBool(Integer result) {
        return SqlHelper.retBool(result);
    }
	
}