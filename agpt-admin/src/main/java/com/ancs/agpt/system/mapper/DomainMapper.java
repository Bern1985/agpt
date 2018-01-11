package com.ancs.agpt.system.mapper;


import com.ancs.agpt.system.entity.Domain;

public interface DomainMapper extends BaseMapper<Domain> {
  Domain findByDomainName(String account);
}