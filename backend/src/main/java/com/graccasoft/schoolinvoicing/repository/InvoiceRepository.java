package com.graccasoft.schoolinvoicing.repository;

import com.graccasoft.schoolinvoicing.model.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface InvoiceRepository extends JpaRepository<Invoice, Long> {
    @Query(value="SELECT i FROM Invoice i WHERE i.student.schoolClass.id = ?1")
    List<Invoice> findBySchoolClass(Long schoolClassId);

    @Query(value="SELECT i FROM Invoice i WHERE i.student.id = ?1")
    List<Invoice> findByStudent(Long studentId);

    Optional<Invoice> findFirstByStudentIdOrderByIdDesc(Long studentId);

}
