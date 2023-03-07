package com.graccasoft.schoolinvoicing.model;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Getter
@Setter
public class Student extends BaseEntity {
    private String firstName;
    private String lastName;
    private Date dateOfBirth;
    private String parentName;
    private String parentPhoneNumber;
    private String parentAddress;

    @ManyToOne
    @JoinColumn(name = "school_class_id")
    private SchoolClass schoolClass;

    @Override
    public String toString() {
        return  firstName + ' ' +lastName ;
    }
}
