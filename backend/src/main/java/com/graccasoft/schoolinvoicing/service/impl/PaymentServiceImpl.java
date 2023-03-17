package com.graccasoft.schoolinvoicing.service.impl;

import com.graccasoft.schoolinvoicing.dto.PaymentDto;
import com.graccasoft.schoolinvoicing.dto.PaymentDtoMapper;
import com.graccasoft.schoolinvoicing.model.Payment;
import com.graccasoft.schoolinvoicing.model.Student;
import com.graccasoft.schoolinvoicing.repository.PaymentRepository;
import com.graccasoft.schoolinvoicing.repository.StudentRepository;
import com.graccasoft.schoolinvoicing.service.PaymentService;
import com.graccasoft.schoolinvoicing.service.SmsService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;
    private final StudentRepository studentRepository;
    private final PaymentDtoMapper paymentDtoMapper;
    private final SmsService smsService;

    public PaymentServiceImpl(PaymentRepository paymentRepository, StudentRepository studentRepository, PaymentDtoMapper paymentDtoMapper, SmsService smsService) {
        this.paymentRepository = paymentRepository;
        this.studentRepository = studentRepository;
        this.paymentDtoMapper = paymentDtoMapper;
        this.smsService = smsService;
    }

    @Override
    @Transactional
    public Payment savePayment(PaymentDto paymentDto) {
        Student student = studentRepository.findById(paymentDto.getStudentId())
                .orElseThrow(() -> new EntityNotFoundException("Student with ID not found"));
        Payment payment = new Payment();
        payment.setStudent( student );
        payment.setAmount(paymentDto.getAmount());

        Payment savedPayment =  paymentRepository.save(payment);

        this.smsService.sendPaymentSms(savedPayment);
        return savedPayment;
    }

    @Override
    public List<PaymentDto> getStudentPayments(Long studentId) {
        return paymentRepository.findAllByStudentId(studentId)
                .stream()
                .map(paymentDtoMapper)
                .toList();
    }

    @Override
    public List<PaymentDto> getAllPayments() {
        return paymentRepository.findAll(Sort.by(Sort.Direction.DESC,"id"))
                .stream()
                .map(paymentDtoMapper)
                .toList();
    }
}
