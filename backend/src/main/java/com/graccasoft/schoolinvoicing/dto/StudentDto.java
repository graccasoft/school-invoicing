package com.graccasoft.schoolinvoicing.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Builder
public class StudentDto {
    private Long id;
    private Date createdAt;
    private String firstName;
    private String lastName;
    private Date dateOfBirth;
    private String parentName;
    private String parentPhoneNumber;
    private String parentAddress;
    private String schoolClassName;
    private Long schoolClassId;
}
