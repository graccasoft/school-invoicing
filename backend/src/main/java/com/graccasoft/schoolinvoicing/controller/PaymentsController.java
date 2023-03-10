package com.graccasoft.schoolinvoicing.controller;

import com.graccasoft.schoolinvoicing.dto.PaymentDto;
import com.graccasoft.schoolinvoicing.model.Payment;
import com.graccasoft.schoolinvoicing.service.PaymentService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController()
@RequestMapping("payments")
public class PaymentsController {

    private final PaymentService paymentService;

    public PaymentsController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping
    public Payment savePayment(PaymentDto paymentDto){
        return paymentService.savePayment(paymentDto);
    }

    @GetMapping
    public List<PaymentDto> getPayments(){
        return paymentService.getAllPayments();
    }
}
