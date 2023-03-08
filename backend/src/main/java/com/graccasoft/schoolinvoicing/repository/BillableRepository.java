package com.graccasoft.schoolinvoicing.repository;

import com.graccasoft.schoolinvoicing.model.Billable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BillableRepository extends JpaRepository<Billable, Long> {
}
