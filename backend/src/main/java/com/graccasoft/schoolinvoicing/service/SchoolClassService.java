package com.graccasoft.schoolinvoicing.service;

import com.graccasoft.schoolinvoicing.model.SchoolClass;

import java.util.List;

public interface SchoolClassService {
    SchoolClass saveSchoolClass(SchoolClass schoolClass);
    List<SchoolClass> getSchoolClasses();
}
