package com.ancs.agpt.system.service;


import com.ancs.agpt.system.entity.RestUrl;

public interface RestUrlService extends BaseService<RestUrl>{
	
	RestUrl selectByUrlAndMethod(String restUrl,String method);
}