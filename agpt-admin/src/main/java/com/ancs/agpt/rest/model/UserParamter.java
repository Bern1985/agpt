package com.ancs.agpt.rest.model;

import java.util.Date;

import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "用户对象") 
public class UserParamter {
	
	@ApiModelProperty(value = "账号", required = true)  
	@NotBlank(message="账号不能为空")
    private String account;
    
	@ApiModelProperty(value = "密码", required = true)  
    @NotBlank(message="密码不能为空")
	@Pattern(regexp="^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,21}$", message="密码必须是6~10位数字和字母的组合")
    private String password;
    
	@ApiModelProperty(value = "姓名", required = true)  
    @NotBlank(message="姓名不能为空")
    private String name;
    @Past
    private Date birthday;
    
    @ApiModelProperty(value = "性别(MALE|FEMALE)", required = true)  
    @Pattern(regexp="MALE|FEMALE",message="性别错误")
    private String sex;

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
