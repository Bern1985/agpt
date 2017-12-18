package com.ancs.agpt.rest.model;


import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotBlank;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "域对象") 
public class DomainWarpper {
	
	@ApiModelProperty(value = "别名", required = false)  
	private String alias;
	
	@ApiModelProperty(value = "名称", required = true) 
	@NotBlank(message="名称不能为空")
    private String name;
	
	@ApiModelProperty(value = "账号", required = true)  
	@NotBlank(message="账号不能为空")
    private String account;
	
	@ApiModelProperty(value = "性别(NORMAL|LOCKED|FORBIDDEN|)", required = true)  
    @Pattern(regexp="NORMAL|LOCKED|FORBIDDEN",message="性别错误")
    private String status;
	
	@ApiModelProperty(value = "过期时间(秒)", required = false)  
    private Long ttl;
}
