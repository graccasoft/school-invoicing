package com.graccasoft.schoolinvoicing.repository;

import com.graccasoft.schoolinvoicing.model.SchoolClass;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SchoolClassRepository extends JpaRepository<SchoolClass, Long> {
}
