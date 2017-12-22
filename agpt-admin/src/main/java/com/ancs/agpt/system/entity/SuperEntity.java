package com.ancs.agpt.system.entity;


import java.time.LocalDateTime;

import com.ancs.agpt.system.toolkit.IdWorker;
import com.fasterxml.jackson.annotation.JsonFormat;

public class SuperEntity {
	
	
    private Long id = IdWorker.getId();
    
    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    private Integer dr = new Integer(0);

    
    private LocalDateTime ts = LocalDateTime.now();
    
    
    public Integer getDr() {
        return dr;
    }

   
    public void setDr(Integer dr) {
        this.dr = dr;
    }

    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
    public LocalDateTime getTs() {
        return ts;
    }

   
    public void setTs(LocalDateTime ts) {
        this.ts = ts;
    }
}
