package com.graccasoft.schoolinvoicing.repository;

import com.graccasoft.schoolinvoicing.model.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InvoiceRepository extends JpaRepository<Invoice, Long> {
}
