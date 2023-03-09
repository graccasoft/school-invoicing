package com.graccasoft.schoolinvoicing.service;

import com.graccasoft.schoolinvoicing.dto.PaymentDto;
import com.graccasoft.schoolinvoicing.model.Payment;

import java.util.List;

public interface PaymentService {
    Payment savePayment(PaymentDto paymentDto);
    List<PaymentDto> getStudentPayments(Long studentId);

    //todo add pagination
    List<PaymentDto> getAllPayments();
}
