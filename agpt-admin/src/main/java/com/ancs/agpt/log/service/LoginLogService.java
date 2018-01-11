package com.ancs.agpt.log.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.elasticsearch.core.query.SearchQuery;

import com.ancs.agpt.log.entity.LoginLog;


public interface LoginLogService {
	LoginLog saveOrUpdate(LoginLog loginLog);

    void delete(LoginLog loginLog);

    LoginLog findOne(Long id);

    Iterable<LoginLog> findAll(Sort sort);

    Page<LoginLog> findByUserName(String userName, Pageable pageable);

    List<LoginLog> findByClientIP(String clientIP);
    
    Page<LoginLog> queryForList(String account,String clientIP,String userName,String failReason,String order,PageRequest pageRequest);
}
