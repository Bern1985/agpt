package com.ancs.agpt.system.service.impl;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ancs.agpt.system.entity.RestUrl;
import com.ancs.agpt.system.mapper.RestUrlMapper;
import com.ancs.agpt.system.service.RestUrlService;

@Service
public class RestUrlServiceImpl extends BaseServiceImpl<RestUrlMapper,RestUrl> implements RestUrlService{

	/*@Transactional(propagation=Propagation.REQUIRED,isolation = Isolation.READ_COMMITTED,rollbackFor = Exception.class,timeout=60)
	public boolean deleteById(Long id) {
		// TODO Auto-generated method stub
		RestUrl url = new RestUrl();
		url.setId(id);
		url.setTs(LocalDateTime.now());
		url.setDr(1);
    	return retBool(baseMapper.update(url));
	}*/

	@Override
	public RestUrl get(Object param) {
		// TODO Auto-generated method stub
		return null;
	}

	/*@Transactional(propagation=Propagation.REQUIRED,isolation = Isolation.READ_COMMITTED,rollbackFor = Exception.class,timeout=60)
	public int insertBatch(Collection<RestUrl> restUrlList) {
		List<RestUrl> data = newArrayList(restUrlList);
		int batchSize = 500;//每次提交500条
		int loop = (int) Math.ceil(data.size() / (double) batchSize);
		List<List<RestUrl>> subList=Lists.partition(data,loop);  
		subList.stream().forEach(list -> baseMapper.insertBatch(list));
		return 0;
	}*/

	@Transactional(readOnly = true, propagation=Propagation.SUPPORTS)
	public RestUrl selectByUrlAndMethod(String restUrl, String method) {
		// TODO Auto-generated method stub
		return baseMapper.selectByUrlAndMethod(restUrl, method);
	}
   
}