package com.graccasoft.schoolinvoicing.model;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class SchoolClass extends BaseEntity {
    private String description;
    private Boolean isActive;
}
