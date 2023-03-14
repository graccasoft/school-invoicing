package com.graccasoft.schoolinvoicing.repository;

import com.graccasoft.schoolinvoicing.model.SystemOption;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SystemOptionRepository extends JpaRepository<SystemOption, Long> {
    Optional<SystemOption> findFirstByName(String name);

}
