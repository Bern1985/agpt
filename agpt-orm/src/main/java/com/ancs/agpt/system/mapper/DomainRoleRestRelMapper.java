package com.ancs.agpt.system.mapper;

import com.ancs.agpt.system.entity.DomainRoleRestRel;

public interface DomainRoleRestRelMapper {
    int deleteByPrimaryKey(Long id);

    int insert(DomainRoleRestRel record);

    int insertSelective(DomainRoleRestRel record);

    DomainRoleRestRel selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(DomainRoleRestRel record);

    int updateByPrimaryKey(DomainRoleRestRel record);
}