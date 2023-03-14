package com.graccasoft.schoolinvoicing.service.impl;

import com.graccasoft.schoolinvoicing.service.SmsService;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class SmsServiceImpl implements SmsService {

    @Value("${twilio-account-sid}")
    private String twilioAccountSID;

    @Value("${twilio-auth-token}")
    private String twilioAuthToken;

    @Value("${twilio-sender-number}")
    private String twilioSender;

    @Override
    public void sendSMS(String phoneNumber, String content)  throws RuntimeException {
        Twilio.init(twilioAccountSID, twilioAuthToken);
        Message message = Message.creator(new PhoneNumber(phoneNumber),
                new PhoneNumber(twilioSender),
                content).create();

        System.out.println("Message STATUS: " + message.getStatus());
        if (message.getStatus() == Message.Status.FAILED ){
            throw new RuntimeException("SMS Not sent");
        }
    }
}
