package com.ancs.agpt.system.entity;

import java.time.Instant;
import java.util.Date;

import com.ancs.agpt.system.entity.enums.Status;

public class Domain extends SuperEntity {

    private String alias;

    private String name;
    
    private String account;

    private Status status;

    private String secret;

    private Date createtime = Date.from(Instant.now());
    
    private Long ttl;

    private static final long serialVersionUID = 1L;

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias == null ? null : alias.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret == null ? null : secret.trim();
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    
    public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}
	

	public Long getTtl() {
		return (ttl == null || ttl == 0) ? 0 : ttl*1000;
	}

	public void setTtl(Long ttl) {
		this.ttl = ttl;
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
        Domain other = (Domain) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getAlias() == null ? other.getAlias() == null : this.getAlias().equals(other.getAlias()))
            && (this.getName() == null ? other.getName() == null : this.getName().equals(other.getName()))
            && (this.getAccount() == null ? other.getAccount() == null : this.getAccount().equals(other.getAccount()))
            && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()))
            && (this.getSecret() == null ? other.getSecret() == null : this.getSecret().equals(other.getSecret()))
            && (this.getTtl() == null ? other.getTtl() == null : this.getTtl().equals(other.getTtl()))
            && (this.getCreatetime() == null ? other.getCreatetime() == null : this.getCreatetime().equals(other.getCreatetime()))
            && (this.getTs() == null ? other.getTs() == null : this.getTs().equals(other.getTs()))
            && (this.getDr() == null ? other.getDr() == null : this.getDr().equals(other.getDr()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getAlias() == null) ? 0 : getAlias().hashCode());
        result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
        result = prime * result + ((getAccount() == null) ? 0 : getAccount().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        result = prime * result + ((getSecret() == null) ? 0 : getSecret().hashCode());
        result = prime * result + ((getTtl() == null) ? 0 : getTtl().hashCode());
        result = prime * result + ((getCreatetime() == null) ? 0 : getCreatetime().hashCode());
        result = prime * result + ((getTs() == null) ? 0 : getTs().hashCode());
        result = prime * result + ((getDr() == null) ? 0 : getDr().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(getId());
        sb.append(", alias=").append(alias);
        sb.append(", name=").append(name);
        sb.append(", account=").append(account);
        sb.append(", status=").append(status);
        sb.append(", secret=").append(secret);
        sb.append(", ttl=").append(ttl);
        sb.append(", createtime=").append(createtime);
        sb.append(", ts=").append(getTs());
        sb.append(", dr=").append(getDr());
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}