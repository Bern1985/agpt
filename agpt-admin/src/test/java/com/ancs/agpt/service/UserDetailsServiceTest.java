package com.ancs.agpt.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.ancs.agpt.AgptApplication;
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = AgptApplication.class)
@WebAppConfiguration
public class UserDetailsServiceTest {
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Test
	public void loadUserByUsername() {
		UserDetails userDetails = userDetailsService.loadUserByUsername("oa123");
		System.out.println(userDetails.getAuthorities());
    }
}
