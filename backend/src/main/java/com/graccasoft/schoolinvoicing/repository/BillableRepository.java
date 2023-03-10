package com.graccasoft.schoolinvoicing.repository;

import com.graccasoft.schoolinvoicing.model.Billable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BillableRepository extends JpaRepository<Billable, Long> {

    List<Billable> getAllBySchoolClassId(Long schoolClassId);
}
