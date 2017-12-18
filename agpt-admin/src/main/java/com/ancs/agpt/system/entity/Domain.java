package com.ancs.agpt.system.entity;

import java.time.Instant;
import java.util.Date;

import com.ancs.agpt.system.entity.enums.Status;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@NoArgsConstructor
public class Domain extends SuperEntity {

    private String alias;

    private String name;
    
    private String account;

    private Status status;

    private String secret;

    private Date createtime = Date.from(Instant.now());
    
    private Long ttl;

    private static final long serialVersionUID = 1L;

	public Domain(String alias, String name, @NonNull String account, Status status, Long ttl) {
		super();
		this.alias = alias;
		this.name = name;
		this.account = account;
		this.status = status;
		this.ttl = ttl;
	}
    
	public Domain(String alias, String name) {
		super();
		this.alias = alias;
		this.name = name;
		
	}

   
}