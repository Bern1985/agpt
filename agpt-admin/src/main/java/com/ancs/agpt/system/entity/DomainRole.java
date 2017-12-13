package com.ancs.agpt.system.entity;

import java.time.Instant;
import java.util.Date;

import lombok.Data;
@Data
public class DomainRole extends SuperEntity{

    private String code;

    private String name;

    private Date createtime = Date.from(Instant.now());

    private static final long serialVersionUID = 1L;

   
}