package com.ancs.agpt.system.entity;

import java.util.Date;

public class Perm extends SuperEntity{
    private String name;
    
    private String permission;

   
    private String description;

  
    private Integer dr;

   
    private Date ts;

    private static final long serialVersionUID = 1L;


    public String getPermission() {
        return permission;
    }

    
    public void setPermission(String permission) {
        this.permission = permission == null ? null : permission.trim();
    }

    
    public String getDescription() {
        return description;
    }

    
    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

   
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

   
    public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	@Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        Perm other = (Perm) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
        	&&	 (this.getName() == null ? other.getName() == null : this.getName().equals(other.getName()))
            && (this.getPermission() == null ? other.getPermission() == null : this.getPermission().equals(other.getPermission()))
            && (this.getDescription() == null ? other.getDescription() == null : this.getDescription().equals(other.getDescription()))
            && (this.getDr() == null ? other.getDr() == null : this.getDr().equals(other.getDr()))
            && (this.getTs() == null ? other.getTs() == null : this.getTs().equals(other.getTs()));
    }

    
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
        result = prime * result + ((getPermission() == null) ? 0 : getPermission().hashCode());
        result = prime * result + ((getDescription() == null) ? 0 : getDescription().hashCode());
        result = prime * result + ((getDr() == null) ? 0 : getDr().hashCode());
        result = prime * result + ((getTs() == null) ? 0 : getTs().hashCode());
        return result;
    }

   
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(getId());
        sb.append(", name=").append(name);
        sb.append(", permission=").append(permission);
        sb.append(", description=").append(description);
        sb.append(", dr=").append(dr);
        sb.append(", ts=").append(ts);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}