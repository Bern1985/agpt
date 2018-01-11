package com.ancs.agpt.log.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.Criteria;
import org.springframework.data.elasticsearch.core.query.CriteriaQuery;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.stereotype.Service;

import com.ancs.agpt.log.entity.LoginLog;
import com.ancs.agpt.log.repository.LoginLogRepository;
import com.ancs.agpt.log.service.LoginLogService;
import com.google.common.base.Optional;

@Service
public class LoginLogServiceImpl implements LoginLogService{
	
	private LoginLogRepository loginLogRepository;
	
	private ElasticsearchTemplate elasticsearchTemplate;
	
	@Autowired
	public void setElasticsearchTemplate(ElasticsearchTemplate elasticsearchTemplate) {
		this.elasticsearchTemplate = elasticsearchTemplate;
	}

	@Autowired
	public void setLoginLogRepository(LoginLogRepository loginLogRepository) {
		this.loginLogRepository = loginLogRepository;
	}

	@Override
	public LoginLog saveOrUpdate(LoginLog loginLog) {
		// TODO Auto-generated method stub
		return loginLogRepository.save(loginLog);
	}

	@Override
	public void delete(LoginLog loginLog) {
		// TODO Auto-generated method stub
		loginLogRepository.delete(loginLog);
	}

	@Override
	public LoginLog findOne(Long id) {
		// TODO Auto-generated method stub
		return loginLogRepository.findOne(id);
	}

	@Override
	public Iterable<LoginLog> findAll(Sort sort) {
		// TODO Auto-generated method stub
		return loginLogRepository.findAll(sort);
	}

	@Override
	public Page<LoginLog> findByUserName(String userName, Pageable pageable) {
		// TODO Auto-generated method stub
		return loginLogRepository.findByUserName(userName, pageable);
	}

	@Override
	public List<LoginLog> findByClientIP(String clientIP) {
		// TODO Auto-generated method stub
		return loginLogRepository.findByClientIP(clientIP);
	}

	@Override
	public Page<LoginLog> queryForList(String account,String clientIP,String userName,String failReason,String order,PageRequest pageRequest) {
		// TODO Auto-generated method stub
		//CriteriaQuery criteriaQuery = new CriteriaQuery();
		Criteria criteria = new Criteria();
		Optional<String> optional = Optional.fromNullable(account);
		if(optional.isPresent()) {
			criteria.and(new Criteria("account").is(account));
		}
		optional = Optional.fromNullable(clientIP);
		if(optional.isPresent()) {
			criteria.and(new Criteria("clientIP").is(clientIP));
		}
		optional = Optional.fromNullable(userName);
		if(optional.isPresent()) {
			criteria.and(new Criteria("userName").is(userName));
		}
		optional = Optional.fromNullable(failReason);
		if(optional.isPresent()) {
			criteria.and(new Criteria("failReason").is(failReason));
		}
		CriteriaQuery criteriaQuery = new CriteriaQuery(criteria);
		criteriaQuery.setPageable(pageRequest);
		optional = Optional.fromNullable(order);
		Sort sort = null;
		if(optional.isPresent()) {
			sort =new Sort(new Sort.Order(Sort.Direction.DESC, order));
		}else {
			sort =new Sort(new Sort.Order(Sort.Direction.DESC, "ts"));
		}
		criteriaQuery.addSort(sort);
		return elasticsearchTemplate.queryForPage(criteriaQuery,LoginLog.class);
	}

}
