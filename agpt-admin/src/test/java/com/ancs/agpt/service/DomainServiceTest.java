package com.ancs.agpt.service;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.ancs.agpt.AgptApplication;
import com.ancs.agpt.system.entity.Domain;
import com.ancs.agpt.system.entity.enums.Status;
import com.ancs.agpt.system.service.DomainService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = AgptApplication.class)
@WebAppConfiguration
public class DomainServiceTest {
	@Autowired
    private DomainService domainService;
	
	@Test
    public void insertName() {
        Domain domain = new Domain();
        domain.setAccount("oa123");
        domain.setName("集团OA");
        domain.setAlias("OA1");
        domain.setSecret("asdfasdf");
        domain.setTtl(60*60*24*30L);
        domain.setStatus(Status.NORMAL);
        assertTrue(domainService.insert(domain));
    }
}
