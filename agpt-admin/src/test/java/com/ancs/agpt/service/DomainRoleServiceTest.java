package com.ancs.agpt.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.ancs.agpt.AgptApplication;
import com.ancs.agpt.system.entity.DomainRole;
import com.ancs.agpt.system.service.DomainRoleService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = AgptApplication.class)
@WebAppConfiguration
public class DomainRoleServiceTest {
	@Autowired
    private DomainRoleService domainRoleService;
	
	@Test
    public void insertRole() {
		DomainRole domainRole = new DomainRole();
		domainRole.setCode("ROLE_DOMAIN_ADMIN");
		domainRole.setName("域管理员");
		domainRoleService.insert(domainRole);
	}
}
