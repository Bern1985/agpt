package com.ancs.agpt.rest.api;


import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ancs.agpt.rest.model.DomainWarpper;
import com.ancs.agpt.rest.model.RestResult;
import com.ancs.agpt.rest.model.UpdateDomainWarpper;
import com.ancs.agpt.system.entity.Domain;
import com.ancs.agpt.system.entity.enums.Status;
import com.ancs.agpt.system.service.DomainService;
import com.ancs.agpt.system.toolkit.EnumUtils;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
@RequestMapping("/api/v1/domains")
@Api(tags="域管理")
@Slf4j
@RestController
public class DomainApi{
	@Autowired
	private DomainService domainService;
	
	@PostMapping(value="",consumes={"application/json"} ,produces = {"application/json"})
	 @ApiOperation(value="添加域", notes="添加域", produces = "application/json")  
   public RestResult add(@RequestBody @Valid DomainWarpper domainWarpper, BindingResult bindingResult) throws Exception {
		if (bindingResult.hasErrors()) {  
			return RestResult.paramterError(bindingResult);
		}
		Domain domain = new Domain(domainWarpper.getAlias() , domainWarpper.getName(), domainWarpper.getAccount(), EnumUtils.descOf(Status.class, domainWarpper.getStatus()), domainWarpper.getTtl()) ;
		domainService.insert(domain);
       return RestResult.ok();
   }
	
	@DeleteMapping("/{id}")
    @ApiOperation(value="通过ID删除域")
    public RestResult delete(@ApiParam(value = "id for Domain", required = true) @PathVariable("id") Long id) throws Exception{
		domainService.deleteById(id);
        return RestResult.ok();
    }
	
	@GetMapping("/{id}")
    @ApiOperation("通过ID查询域")
    public RestResult findById(@ApiParam(value = "id for Domain", required = true) @PathVariable("id") Long id)  throws Exception {
		Domain domain = domainService.selectById(id);
        return RestResult.warperOk(domain);
    }
	
	@PutMapping("")
    @ApiOperation("更新域")
    public RestResult update(@RequestBody @Valid UpdateDomainWarpper domainParamter,BindingResult bindingResult)  throws Exception {
		if (bindingResult.hasErrors()) {  
			return RestResult.paramterError(bindingResult);
		}
		Domain domain = new Domain(domainParamter.getAlias() , domainParamter.getName());
		domainService.update(domain);
        return RestResult.ok();
    }
}
