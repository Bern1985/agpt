package com.ancs.agpt.rest.model;


import java.time.LocalDateTime;

import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotBlank;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@ApiModel(value = "登录日志对象") 
public class LoginLogWarpper {
	@ApiModelProperty(value = "用户ID", required = false)  
	private String userId; 
	
	@ApiModelProperty(value = "用户名称", required = false)  
	private String userName;
	
	@ApiModelProperty(value = "账户名称", required = true)  
    @NotBlank(message="账户名称不能为空")
	private String account;
	
	@ApiModelProperty(value = "客户端IP", required = true)  
    @NotBlank(message="客户端IP不能为空")
	private String clientIP; 
	
	@ApiModelProperty(value = "成功(0|1) (0: FAIL ,1: SUCCESS)", required = true)  
	private Integer succeed;
	 
	 @ApiModelProperty(value = "注销时间", required = false)  
	private LocalDateTime loginOutTime;
	
	 @ApiModelProperty(value = "登录失败原因", required = false)  
	 private String failReason;

	public LoginLogWarpper(String userId, String userName, String account, String clientIP, Integer succeed,
			LocalDateTime loginOutTime, String failReason) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.account = account;
		this.clientIP = clientIP;
		this.succeed = succeed;
		this.loginOutTime = loginOutTime;
		this.failReason = failReason;
	}
	
	
}
