package com.ancs.agpt.service;


import static org.junit.Assert.assertTrue;

import java.sql.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.ancs.agpt.AgptApplication;
import com.ancs.agpt.mybatis.plugin.Page;
import com.ancs.agpt.system.entity.User;
import com.ancs.agpt.system.entity.enums.Sex;
import com.ancs.agpt.system.entity.enums.Status;
import com.ancs.agpt.system.service.UserService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = AgptApplication.class)
@WebAppConfiguration
public class UserServiceTest {
	@Autowired
    private UserService userService;
	
	/*@Test
    public void insertName() {
        User user = new User();
        user.setAccount("zhanghua");
        user.setName("张华");
        user.setPassword("Agpt123456");
        user.setBirthday(new Date(1985,1,27));
        user.setSex(Sex.MALE);
        user.setEmail("zhanghua850127@163.com");
        user.setPhone("15110068272");
        user.setStatus(Status.NORMAL);
        assertTrue(userService.insert(user));
    }*/
	
	@Test
    public void selectPage() {
		Page<User> page = new Page(1,Integer.MAX_VALUE);
		System.out.println("first Query ==========================");
		page = userService.selectPage(page);
		//assertTrue(page.getSize()>0);
		page.getRecords().stream().forEach(System.out::println);
		System.out.println("second Query ==========================");
		page = userService.selectPage(page);
		//assertTrue(page.getSize()>0);
		page.getRecords().stream().forEach(System.out::println);
		System.out.println("third Query ==========================");
		page = userService.selectPage(page);
		//assertTrue(page.getSize()>0);
		page.getRecords().stream().forEach(System.out::println);
    }
	/*@Test
	public void createUser() {
        User user = new User();
        user.setName("超级管理员");
        user.setPassword("Agpt123456");
        user.setEmail("zhanghua850127@163.com");
        user.setPhone("15110068272");
        user.setAccount("admin");
        user.setStatus(Status.NORMAL);
        System.out.println(user);
    }*/
}
