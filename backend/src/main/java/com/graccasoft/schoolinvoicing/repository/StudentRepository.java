package com.graccasoft.schoolinvoicing.repository;

import com.graccasoft.schoolinvoicing.model.SchoolClass;
import com.graccasoft.schoolinvoicing.model.Student;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {

    List<Student> findAllBySchoolClass(SchoolClass schoolClass, Pageable pageable);
    List<Student> findAllByLastNameContainingIgnoreCase(String lastName, Pageable pageable);
    List<Student> findAllByFirstNameAndLastName(String firstName, String lastName);
}
