package com.ancs.agpt.rest.model;

import org.hibernate.validator.constraints.NotBlank;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
@Data
@ApiModel(value = "域对象") 
public class UpdateDomainWarpper {
	@ApiModelProperty(value = "别名", required = false)  
	private String alias;
	
	@ApiModelProperty(value = "名称", required = true) 
	@NotBlank(message="名称不能为空")
    private String name;
	
}
