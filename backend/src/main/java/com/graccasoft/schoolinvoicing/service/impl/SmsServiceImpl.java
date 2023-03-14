package com.graccasoft.schoolinvoicing.service.impl;

import com.graccasoft.schoolinvoicing.model.Payment;
import com.graccasoft.schoolinvoicing.model.SystemOption;
import com.graccasoft.schoolinvoicing.service.SmsService;
import com.graccasoft.schoolinvoicing.service.SystemOptionService;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class SmsServiceImpl implements SmsService {

    @Value("${twilio-account-sid}")
    private String twilioAccountSID;

    @Value("${twilio-auth-token}")
    private String twilioAuthToken;

    @Value("${twilio-sender-number}")
    private String twilioSender;

    private final SystemOptionService systemOptionService;

    public SmsServiceImpl(SystemOptionService systemOptionService) {
        this.systemOptionService = systemOptionService;
    }

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

    @Override
    public void sendPaymentSms(Payment payment) {
        String message = "Payment of " + payment.getAmount() + " received for " + payment.getStudent();
        SystemOption smsReceivers = systemOptionService.getSystemOption("PAYMENT_SMS_RECEIVERS");
        if(smsReceivers == null)
            return;

        List<String> phoneNumbers = Arrays.asList( smsReceivers.getValue().split(",") );
        phoneNumbers.forEach(phoneNumber->{
            sendSMS(phoneNumber,message);
        });
    }
}
