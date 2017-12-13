package com.ancs.agpt.system.entity;

import lombok.Data;

@Data
public class RestUrl extends SuperEntity{

    private String code;

    private String restUrl;

    private String description;
    
    private String method;

    private static final long serialVersionUID = 1L;
   
}