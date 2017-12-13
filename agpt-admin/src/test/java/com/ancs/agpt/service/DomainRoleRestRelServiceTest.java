package com.ancs.agpt.service;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.ancs.agpt.AgptApplication;
import com.ancs.agpt.system.entity.DomainRoleRel;
import com.ancs.agpt.system.entity.DomainRoleRestRel;
import com.ancs.agpt.system.service.DomainRoleRelService;
import com.ancs.agpt.system.service.DomainRoleRestRelService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = AgptApplication.class)
@WebAppConfiguration
public class DomainRoleRestRelServiceTest {
	@Autowired
    private DomainRoleRestRelService domainRoleRestRelService;
	
	@Test
	public void batchInsertRoleRel() {
		List<DomainRoleRestRel> list = new ArrayList<>();
		DomainRoleRestRel domainRoleRel = new DomainRoleRestRel();
		domainRoleRel.setRestId(939072018664497154L);
		domainRoleRel.setRoleId(940059410825326593L);
		list.add(domainRoleRel);
		domainRoleRel = new DomainRoleRestRel();
		domainRoleRel.setRestId(939072018677080066L);
		domainRoleRel.setRoleId(940059410825326593L);
		list.add(domainRoleRel);
		domainRoleRel = new DomainRoleRestRel();
		domainRoleRel.setRestId(939072018685468673L);
		domainRoleRel.setRoleId(940059410825326593L);
		list.add(domainRoleRel);
		domainRoleRel = new DomainRoleRestRel();
		domainRoleRel.setRestId(939072018685468674L);
		domainRoleRel.setRoleId(940059410825326593L);
		list.add(domainRoleRel);
		domainRoleRel = new DomainRoleRestRel();
		domainRoleRel.setRestId(939072018693857282L);
		domainRoleRel.setRoleId(940059410825326593L);
		list.add(domainRoleRel);
		domainRoleRel = new DomainRoleRestRel();
		domainRoleRel.setRestId(939072018698051586L);
		domainRoleRel.setRoleId(940059410825326593L);
		list.add(domainRoleRel);
		domainRoleRel = new DomainRoleRestRel();
		domainRoleRel.setRestId(939072018702245890L);
		domainRoleRel.setRoleId(940059410825326593L);
		list.add(domainRoleRel);
		domainRoleRestRelService.insertBatch(list);
	}
}
