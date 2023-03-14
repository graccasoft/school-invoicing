package com.graccasoft.schoolinvoicing.service;

public interface SmsService {
    void sendSMS(String phoneNumber, String content);
}
