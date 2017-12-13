package com.ancs.agpt.system.entity;

import com.ancs.agpt.system.toolkit.IdWorker;

import lombok.Data;

@Data
public class DomainRoleRestRel implements java.io.Serializable{
	private Long id = IdWorker.getId();
	
    private Long restId;

    private Long roleId;

    private static final long serialVersionUID = 1L;

}