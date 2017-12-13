package com.ancs.agpt.system.entity;

import java.io.Serializable;
import java.util.Date;

public class OperationLog implements Serializable {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column T_ANCS_SYSTEM_OPERATION_LOG.id
     *
     * @mbggenerated
     */
    private String id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column T_ANCS_SYSTEM_OPERATION_LOG.username
     *
     * @mbggenerated
     */
    private String username;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column T_ANCS_SYSTEM_OPERATION_LOG.operation
     *
     * @mbggenerated
     */
    private String operation;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column T_ANCS_SYSTEM_OPERATION_LOG.method
     *
     * @mbggenerated
     */
    private String method;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column T_ANCS_SYSTEM_OPERATION_LOG.params
     *
     * @mbggenerated
     */
    private String params;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column T_ANCS_SYSTEM_OPERATION_LOG.costtime
     *
     * @mbggenerated
     */
    private Integer costtime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column T_ANCS_SYSTEM_OPERATION_LOG.ip
     *
     * @mbggenerated
     */
    private String ip;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column T_ANCS_SYSTEM_OPERATION_LOG.createtime
     *
     * @mbggenerated
     */
    private Date createtime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column T_ANCS_SYSTEM_OPERATION_LOG.desciption
     *
     * @mbggenerated
     */
    private String desciption;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table T_ANCS_SYSTEM_OPERATION_LOG
     *
     * @mbggenerated
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column T_ANCS_SYSTEM_OPERATION_LOG.id
     *
     * @return the value of T_ANCS_SYSTEM_OPERATION_LOG.id
     *
     * @mbggenerated
     */
    public String getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column T_ANCS_SYSTEM_OPERATION_LOG.id
     *
     * @param id the value for T_ANCS_SYSTEM_OPERATION_LOG.id
     *
     * @mbggenerated
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column T_ANCS_SYSTEM_OPERATION_LOG.username
     *
     * @return the value of T_ANCS_SYSTEM_OPERATION_LOG.username
     *
     * @mbggenerated
     */
    public String getUsername() {
        return username;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column T_ANCS_SYSTEM_OPERATION_LOG.username
     *
     * @param username the value for T_ANCS_SYSTEM_OPERATION_LOG.username
     *
     * @mbggenerated
     */
    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column T_ANCS_SYSTEM_OPERATION_LOG.operation
     *
     * @return the value of T_ANCS_SYSTEM_OPERATION_LOG.operation
     *
     * @mbggenerated
     */
    public String getOperation() {
        return operation;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column T_ANCS_SYSTEM_OPERATION_LOG.operation
     *
     * @param operation the value for T_ANCS_SYSTEM_OPERATION_LOG.operation
     *
     * @mbggenerated
     */
    public void setOperation(String operation) {
        this.operation = operation == null ? null : operation.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column T_ANCS_SYSTEM_OPERATION_LOG.method
     *
     * @return the value of T_ANCS_SYSTEM_OPERATION_LOG.method
     *
     * @mbggenerated
     */
    public String getMethod() {
        return method;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column T_ANCS_SYSTEM_OPERATION_LOG.method
     *
     * @param method the value for T_ANCS_SYSTEM_OPERATION_LOG.method
     *
     * @mbggenerated
     */
    public void setMethod(String method) {
        this.method = method == null ? null : method.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column T_ANCS_SYSTEM_OPERATION_LOG.params
     *
     * @return the value of T_ANCS_SYSTEM_OPERATION_LOG.params
     *
     * @mbggenerated
     */
    public String getParams() {
        return params;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column T_ANCS_SYSTEM_OPERATION_LOG.params
     *
     * @param params the value for T_ANCS_SYSTEM_OPERATION_LOG.params
     *
     * @mbggenerated
     */
    public void setParams(String params) {
        this.params = params == null ? null : params.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column T_ANCS_SYSTEM_OPERATION_LOG.costtime
     *
     * @return the value of T_ANCS_SYSTEM_OPERATION_LOG.costtime
     *
     * @mbggenerated
     */
    public Integer getCosttime() {
        return costtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column T_ANCS_SYSTEM_OPERATION_LOG.costtime
     *
     * @param costtime the value for T_ANCS_SYSTEM_OPERATION_LOG.costtime
     *
     * @mbggenerated
     */
    public void setCosttime(Integer costtime) {
        this.costtime = costtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column T_ANCS_SYSTEM_OPERATION_LOG.ip
     *
     * @return the value of T_ANCS_SYSTEM_OPERATION_LOG.ip
     *
     * @mbggenerated
     */
    public String getIp() {
        return ip;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column T_ANCS_SYSTEM_OPERATION_LOG.ip
     *
     * @param ip the value for T_ANCS_SYSTEM_OPERATION_LOG.ip
     *
     * @mbggenerated
     */
    public void setIp(String ip) {
        this.ip = ip == null ? null : ip.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column T_ANCS_SYSTEM_OPERATION_LOG.createtime
     *
     * @return the value of T_ANCS_SYSTEM_OPERATION_LOG.createtime
     *
     * @mbggenerated
     */
    public Date getCreatetime() {
        return createtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column T_ANCS_SYSTEM_OPERATION_LOG.createtime
     *
     * @param createtime the value for T_ANCS_SYSTEM_OPERATION_LOG.createtime
     *
     * @mbggenerated
     */
    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column T_ANCS_SYSTEM_OPERATION_LOG.desciption
     *
     * @return the value of T_ANCS_SYSTEM_OPERATION_LOG.desciption
     *
     * @mbggenerated
     */
    public String getDesciption() {
        return desciption;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column T_ANCS_SYSTEM_OPERATION_LOG.desciption
     *
     * @param desciption the value for T_ANCS_SYSTEM_OPERATION_LOG.desciption
     *
     * @mbggenerated
     */
    public void setDesciption(String desciption) {
        this.desciption = desciption == null ? null : desciption.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_ANCS_SYSTEM_OPERATION_LOG
     *
     * @mbggenerated
     */
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
        OperationLog other = (OperationLog) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getUsername() == null ? other.getUsername() == null : this.getUsername().equals(other.getUsername()))
            && (this.getOperation() == null ? other.getOperation() == null : this.getOperation().equals(other.getOperation()))
            && (this.getMethod() == null ? other.getMethod() == null : this.getMethod().equals(other.getMethod()))
            && (this.getParams() == null ? other.getParams() == null : this.getParams().equals(other.getParams()))
            && (this.getCosttime() == null ? other.getCosttime() == null : this.getCosttime().equals(other.getCosttime()))
            && (this.getIp() == null ? other.getIp() == null : this.getIp().equals(other.getIp()))
            && (this.getCreatetime() == null ? other.getCreatetime() == null : this.getCreatetime().equals(other.getCreatetime()))
            && (this.getDesciption() == null ? other.getDesciption() == null : this.getDesciption().equals(other.getDesciption()));
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_ANCS_SYSTEM_OPERATION_LOG
     *
     * @mbggenerated
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getUsername() == null) ? 0 : getUsername().hashCode());
        result = prime * result + ((getOperation() == null) ? 0 : getOperation().hashCode());
        result = prime * result + ((getMethod() == null) ? 0 : getMethod().hashCode());
        result = prime * result + ((getParams() == null) ? 0 : getParams().hashCode());
        result = prime * result + ((getCosttime() == null) ? 0 : getCosttime().hashCode());
        result = prime * result + ((getIp() == null) ? 0 : getIp().hashCode());
        result = prime * result + ((getCreatetime() == null) ? 0 : getCreatetime().hashCode());
        result = prime * result + ((getDesciption() == null) ? 0 : getDesciption().hashCode());
        return result;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_ANCS_SYSTEM_OPERATION_LOG
     *
     * @mbggenerated
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", username=").append(username);
        sb.append(", operation=").append(operation);
        sb.append(", method=").append(method);
        sb.append(", params=").append(params);
        sb.append(", costtime=").append(costtime);
        sb.append(", ip=").append(ip);
        sb.append(", createtime=").append(createtime);
        sb.append(", desciption=").append(desciption);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}