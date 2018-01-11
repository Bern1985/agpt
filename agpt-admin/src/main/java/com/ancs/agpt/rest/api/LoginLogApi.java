package com.ancs.agpt.rest.api;


import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ancs.agpt.log.entity.LoginLog;
import com.ancs.agpt.log.service.LoginLogService;
import com.ancs.agpt.rest.model.LoginLogWarpper;
import com.ancs.agpt.rest.model.RestResult;
import com.ancs.agpt.system.entity.User;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@RequestMapping("/api/v1/loginlog")
@Api(tags="登录日志管理")
@Slf4j
@RestController
public class LoginLogApi {
	@Autowired
	private LoginLogService loginLogService;
	
	@PostMapping(value="",consumes={"application/json"} ,produces = {"application/json"})
	 @ApiOperation(value="增加日志", notes="增加日志", produces = "application/json")  
   public RestResult add(@RequestBody @Valid LoginLogWarpper loginLogWarpper, BindingResult bindingResult) throws Exception {
		if (bindingResult.hasErrors()) {  
			return RestResult.paramterError(bindingResult);
		}
		LoginLog loginLog = new LoginLog(loginLogWarpper.getUserId(), loginLogWarpper.getUserName(), loginLogWarpper.getAccount(), loginLogWarpper.getClientIP(), loginLogWarpper.getFailReason(), loginLogWarpper.getSucceed(),
				loginLogWarpper.getLoginOutTime()) ;
		loginLogService.saveOrUpdate(loginLog);
		log.info("save login log " + loginLog);
       return RestResult.ok();
   }
	
	@GetMapping("")
    @ApiOperation("查询日志列表")
	@ApiImplicitParams( value = {
	        @ApiImplicitParam(paramType = "query", name = "account", dataType = "String", required = false, value = "用户账户"),
	        @ApiImplicitParam(paramType = "query", name = "clientIP", dataType = "String", required = false, value = "客户端IP"),
	        @ApiImplicitParam(paramType = "query", name = "userName", dataType = "String", required = false, value = "用户名称"),
	        @ApiImplicitParam(paramType = "query", name = "failReason", dataType = "String", required = false, value = "失败原因"),
	        @ApiImplicitParam(paramType = "query", name = "order", dataType = "String", required = false, value = "排序字段"),
	        @ApiImplicitParam(paramType = "query",name = "page", value = "当前页码", required = true, dataType = "integer"),
	        @ApiImplicitParam(paramType = "query",name = "limit", value = "每页条数", required = true, dataType = "integer")
	})
    public RestResult selectPage(@RequestParam(value="account",required=false) String account,
    		@RequestParam(value="clientIP", required=false) String clientIP,
    		@RequestParam(value="userName", required=false) String userName,
    		@RequestParam(value="failReason", required=false) String failReason,
            @RequestParam(value="order",required=false) String order,
            @RequestParam(name = "page", defaultValue = "1") int page,
            @RequestParam(name = "limit", defaultValue = "10") int limit)  throws Exception {
		PageRequest pageRequest = new PageRequest(page,limit);
		Page<LoginLog> page1 = loginLogService.queryForList(account, clientIP, userName, failReason, order, pageRequest);
        return RestResult.warperOk(page1);
    }
}
