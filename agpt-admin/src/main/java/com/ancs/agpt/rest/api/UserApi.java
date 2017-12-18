package com.ancs.agpt.rest.api;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.Date;

import javax.validation.Valid;

import com.ancs.agpt.mybatis.plugin.Page;
import com.ancs.agpt.rest.model.RestResult;
import com.ancs.agpt.rest.model.UpdateUserWarpper;
import com.ancs.agpt.rest.model.UserWarpper;
import com.ancs.agpt.system.entity.User;
import com.ancs.agpt.system.entity.enums.Sex;
import com.ancs.agpt.system.entity.enums.Status;
import com.ancs.agpt.system.service.UserService;
import com.ancs.agpt.system.toolkit.EnumUtils;

@RequestMapping("/api/v1/users")
@Api(tags="用户管理")
@Slf4j
@RestController
public class UserApi {
	@Autowired
	private UserService userService;
	
	@PostMapping(value="",consumes={"application/json"} ,produces = {"application/json"})
	 @ApiOperation(value="添加用户", notes="添加用户", produces = "application/json")  
    public RestResult add(@RequestBody @Valid UserWarpper userParamter, BindingResult bindingResult) throws Exception {
		if (bindingResult.hasErrors()) {  
			return RestResult.paramterError(bindingResult);
		}
		User user = new User(userParamter.getAccount(), userParamter.getPassword(), userParamter.getName(), userParamter.getBirthday(),EnumUtils.descOf(Sex.class, userParamter.getSex()) , userParamter.getEmail(), userParamter.getPhone(),userParamter.getTelphone(),  EnumUtils.descOf(Status.class, userParamter.getStatus()));
		userService.insert(user);
        return RestResult.ok();
    }
	
	@DeleteMapping("/{id}")
    @ApiOperation(value="通过ID删除用户")
    public RestResult delete(@ApiParam(value = "id for User", required = true) @PathVariable("id") Long id) throws Exception{
        userService.deleteById(id);
        return RestResult.ok();
    }
	
	@GetMapping("/{id}")
    @ApiOperation("通过ID查询用户")
    public RestResult findById(@ApiParam(value = "id for User", required = true) @PathVariable("id") Long id)  throws Exception {
		User user = userService.selectById(id);
        return RestResult.warperOk(user);
    }
	
	@PutMapping("")
    @ApiOperation("更新用户")
    public RestResult update(@RequestBody @Valid UpdateUserWarpper userParamter,BindingResult bindingResult)  throws Exception {
		if (bindingResult.hasErrors()) {  
			return RestResult.paramterError(bindingResult);
		}
		User user = new User( userParamter.getEmail(),userParamter.getPhone(), userParamter.getTelphone(),  EnumUtils.descOf(Status.class, userParamter.getStatus()), Date.from(Instant.now()));
		userService.update(user);
        return RestResult.ok();
    }
	
	
	@PutMapping("/lastlogin/{id}")
    @ApiOperation("更新用户最后登录时间")
    public RestResult updateLastLoginTime(@ApiParam(value = "id for User", required = true) @PathVariable("id") Long id){
		userService.updateLastLoginTime(id);
		return RestResult.ok();
    }
	
	@PutMapping("/status/{id}/{status}")
    @ApiOperation("更新用户状态")
    public RestResult setStatus(@ApiParam(value = "用户ID", required = true) @PathVariable("id") Long id, @ApiParam(value = "用户状态", required = true) @PathVariable("status") String status){
		User user = new User();
		user.setId(id);
		user.setStatus(EnumUtils.descOf(Status.class, status));
		userService.setStatus(user);
		return RestResult.ok();
    }
	
	@GetMapping("")
    @ApiOperation("查询用户列表")
	@ApiImplicitParams( value = {
	        @ApiImplicitParam(paramType = "query", name = "status", dataType = "String", required = false, value = "用户状态"),
	        @ApiImplicitParam(paramType = "query", name = "deptID", dataType = "Long", required = false, value = "部门ID"),
	        @ApiImplicitParam(paramType = "query", name = "order", dataType = "String", required = false, value = "排序字段"),
	        @ApiImplicitParam(paramType = "query",name = "page", value = "当前页码", required = true, dataType = "integer"),
	        @ApiImplicitParam(paramType = "query",name = "limit", value = "每页条数", required = true, dataType = "integer")
	})
    public RestResult selectPage(@RequestParam(value="status",required=false) String status,
    		@RequestParam(value="deptID", required=false) String deptID,
            @RequestParam(value="order",required=false) String order,
            @RequestParam(name = "page", defaultValue = "1") int page,
            @RequestParam(name = "limit", defaultValue = "10") int limit)  throws Exception {
		
		Page<User> page1 = new Page(page,limit);
		if(order!=null) {
			page1.setOrderByField(order);
		}
		page1 = userService.selectPage(page1);
        return RestResult.warperOk(page1);
    }
}
