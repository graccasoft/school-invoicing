package com.graccasoft.schoolinvoicing.dto;

import com.graccasoft.schoolinvoicing.model.Payment;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class PaymentDtoMapper implements Function<Payment, PaymentDto> {
    @Override
    public PaymentDto apply(Payment payment) {
        return PaymentDto.builder()
                .id(payment.getId())
                .amount(payment.getAmount())
                .createdAt(payment.getCreatedAt())
                .studentId(payment.getStudent().getId())
                .build();

    }
}
