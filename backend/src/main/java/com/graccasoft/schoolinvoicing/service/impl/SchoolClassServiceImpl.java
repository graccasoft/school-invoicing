package com.graccasoft.schoolinvoicing.service.impl;

import com.graccasoft.schoolinvoicing.model.SchoolClass;
import com.graccasoft.schoolinvoicing.repository.SchoolClassRepository;
import com.graccasoft.schoolinvoicing.service.SchoolClassService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SchoolClassServiceImpl implements SchoolClassService {

    private final SchoolClassRepository schoolClassRepository;

    public SchoolClassServiceImpl(SchoolClassRepository schoolClassRepository) {
        this.schoolClassRepository = schoolClassRepository;
    }

    @Override
    @Transactional
    public SchoolClass saveSchoolClass(SchoolClass schoolClass) {
        schoolClassRepository.save(schoolClass);
        return null;
    }

    @Override
    public List<SchoolClass> getSchoolClasses() {
        return schoolClassRepository.findAll();
    }
}
