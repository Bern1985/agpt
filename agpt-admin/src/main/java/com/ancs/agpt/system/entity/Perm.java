package com.ancs.agpt.system.entity;

import java.util.Date;

import lombok.Data;

@Data
public class Perm extends SuperEntity{
    private String name;
    
    private String permission;

   
    private String description;

    private static final long serialVersionUID = 1L;


   
}