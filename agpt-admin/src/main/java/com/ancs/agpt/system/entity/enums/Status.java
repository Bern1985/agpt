package com.ancs.agpt.system.entity.enums;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonValue;

public enum  Status implements IEnum{
	
	NORMAL(0,"NORMAL"),LOCKED(1,"LOCKED"),FORBIDDEN(2,"FORBIDDEN");

    private int value;
    private String desc;

    Status(final int value, final String desc) {
        this.value = value;
        this.desc = desc;
    }

    @Override
    public Serializable getValue() {
        return this.value;
    }

    @JsonValue
    public String getDesc(){
        return this.desc;
    }
    
}
