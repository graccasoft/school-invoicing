package com.graccasoft.schoolinvoicing.controller;

import com.graccasoft.schoolinvoicing.dto.PaymentDto;
import com.graccasoft.schoolinvoicing.model.Payment;
import com.graccasoft.schoolinvoicing.service.PaymentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping("payments")
public class PaymentsController {

    private final PaymentService paymentService;

    public PaymentsController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping
    public Payment savePayment(@RequestBody PaymentDto paymentDto){
        return paymentService.savePayment(paymentDto);
    }

    @GetMapping
    public List<PaymentDto> getPayments(){
        return paymentService.getAllPayments();
    }
}
