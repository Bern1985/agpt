package com.ancs.agpt.rest.model;



import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Email;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "用户对象") 
public class UpdateUserWarpper {
	
    @Email(message="邮箱格式错误")
    private String email;

    
    @ApiModelProperty(value = "联系方式", required = false)  
    private String phone;

    @ApiModelProperty(value = "状态", required = false)  
    private String status;
    
    @ApiModelProperty(value = "手机", required = false)  
    @Pattern(regexp="^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$", message="手机号格式不正确")
    private String telphone;
    
}
