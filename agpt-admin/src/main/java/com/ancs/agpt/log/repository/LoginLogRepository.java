package com.ancs.agpt.log.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import com.ancs.agpt.log.entity.LoginLog;


public interface LoginLogRepository extends ElasticsearchRepository<LoginLog, Long> {
	 	
		Page<LoginLog> findByUserName(String userName, Pageable pageable);

	    List<LoginLog> findByClientIP(String clientIP);
}
