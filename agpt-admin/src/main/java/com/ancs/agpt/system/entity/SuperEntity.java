package com.ancs.agpt.system.entity;


import java.time.Instant;
import java.util.Date;

import com.ancs.agpt.system.toolkit.IdWorker;

public class SuperEntity {
	
	
    private Long id = IdWorker.getId();
    
    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    private Integer dr = new Integer(0);

    
    private Date ts = Date.from(Instant.now());
    
    
    public Integer getDr() {
        return dr;
    }

   
    public void setDr(Integer dr) {
        this.dr = dr;
    }

   
    public Date getTs() {
        return ts;
    }

   
    public void setTs(Date ts) {
        this.ts = ts;
    }
}
