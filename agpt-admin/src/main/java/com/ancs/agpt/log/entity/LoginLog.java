package com.ancs.agpt.log.entity;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import com.ancs.agpt.system.toolkit.IdWorker;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Document(indexName = "agpt", type = "loginlog")
public class LoginLog implements java.io.Serializable {

	/**
	* 
	*/
	private static final long serialVersionUID = -8858723131754701512L;
	@Id
	private Long id = IdWorker.getId();
	
	private String userId;
	
	private String userName;
	
	private String account;
	
	private String clientIP;
	
	private LocalDateTime loginOnTime  = LocalDateTime.now();
	
	private String failReason;
	
	private Integer succeed;
	
	private LocalDateTime loginOutTime;
	
	private Integer dr = new Integer(0);
	
	private LocalDateTime ts = LocalDateTime.now();

	public LoginLog(String userId, String userName, String account, String clientIP, String failReason, Integer succeed,
			LocalDateTime loginOutTime) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.account = account;
		this.clientIP = clientIP;
		this.failReason = failReason;
		this.succeed = succeed;
		this.loginOutTime = loginOutTime;
	}

	

}
