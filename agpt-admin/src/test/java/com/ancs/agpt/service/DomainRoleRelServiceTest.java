package com.ancs.agpt.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.ancs.agpt.AgptApplication;
import com.ancs.agpt.system.entity.DomainRoleRel;
import com.ancs.agpt.system.service.DomainRoleRelService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = AgptApplication.class)
@WebAppConfiguration
public class DomainRoleRelServiceTest {
	
	@Autowired
	private DomainRoleRelService domainRoleRelService;
	@Test
	public void insertDomainRoleRel() {
		DomainRoleRel domainRoleRel = new DomainRoleRel();
		domainRoleRel.setDomainId(938674977253720065L);
		domainRoleRel.setDomainRoleId(940059410825326593L);
		domainRoleRelService.insert(domainRoleRel);
	}
}
