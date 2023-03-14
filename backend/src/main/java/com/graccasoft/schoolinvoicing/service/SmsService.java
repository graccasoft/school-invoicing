package com.graccasoft.schoolinvoicing.service;

import com.graccasoft.schoolinvoicing.model.Payment;

public interface SmsService {
    void sendSMS(String phoneNumber, String content);
    void sendPaymentSms(Payment payment);
}
