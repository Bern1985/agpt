package com.ancs.agpt.system.mapper;

import java.util.List;

import com.ancs.agpt.system.entity.DomainRole;

public interface DomainRoleMapper extends BaseMapper<DomainRole> {
	List<DomainRole> findByDomainId(Long domainId);
}