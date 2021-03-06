package com.ancs.agpt.system.entity;

import com.ancs.agpt.system.toolkit.IdWorker;

import lombok.Data;
@Data
public class DomainRoleRel implements java.io.Serializable{
	
	private Long id = IdWorker.getId();
	
    private Long domainId;

    private Long domainRoleId;

    private static final long serialVersionUID = 1L;

}