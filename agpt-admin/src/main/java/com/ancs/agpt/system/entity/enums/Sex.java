package com.ancs.agpt.system.entity.enums;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonValue;

public enum Sex implements IEnum{
	MALE(0,"MALE"),FEMALE(1,"FEMALE");

    private int value;
    private String desc;

    Sex(final int value, final String desc) {
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
