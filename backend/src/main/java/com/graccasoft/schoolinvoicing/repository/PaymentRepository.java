package com.graccasoft.schoolinvoicing.repository;

import com.graccasoft.schoolinvoicing.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
    List<Payment> findAllByStudentId(Long studentId);
}
