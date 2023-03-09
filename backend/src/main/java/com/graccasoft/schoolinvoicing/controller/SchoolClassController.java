package com.graccasoft.schoolinvoicing.controller;

import com.graccasoft.schoolinvoicing.model.SchoolClass;
import com.graccasoft.schoolinvoicing.service.SchoolClassService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("school-class")
public class SchoolClassController {

    private final SchoolClassService schoolClassService;

    public SchoolClassController(SchoolClassService schoolClassService) {
        this.schoolClassService = schoolClassService;
    }

    @PostMapping
    public ResponseEntity<SchoolClass> saveSchoolClass(@RequestBody SchoolClass schoolClass){
        SchoolClass savedSchoolClass = schoolClassService.saveSchoolClass(schoolClass);

        return new ResponseEntity<>(savedSchoolClass, HttpStatus.CREATED);
    }

    @GetMapping
    public List<SchoolClass> getSchoolClasses(){
        return schoolClassService.getSchoolClasses();
    }
}
