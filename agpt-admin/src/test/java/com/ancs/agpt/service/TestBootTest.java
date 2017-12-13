package com.ancs.agpt.service;

import javax.annotation.Resource;

import org.jasypt.encryption.StringEncryptor;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.ancs.agpt.AgptApplication;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = AgptApplication.class)
@WebAppConfiguration
public class TestBootTest {
	
	@Resource(name="encryptorBean")
    StringEncryptor stringEncryptor;//密码解码器自动注入

    @Test
    public void test() {
        System.out.println(stringEncryptor.encrypt("123456"));
    }
}
