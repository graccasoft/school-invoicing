package com.graccasoft.schoolinvoicing.service.impl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class SmsServiceImplTest {

    @Autowired
    private SmsServiceImpl smsService;

    @Test
    void shouldSendSMS(){
        Assertions.assertDoesNotThrow(()->{
            smsService.sendSMS("263779302259","Test from Payment SYS.");
        });
    }
}