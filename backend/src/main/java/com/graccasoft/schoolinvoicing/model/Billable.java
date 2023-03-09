package com.graccasoft.schoolinvoicing.model;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Getter
@Setter
public class Billable extends BaseEntity {
    private String description;

    @ManyToOne
    @JoinColumn(name = "school_class_id")
    private SchoolClass schoolClass;
    private BigDecimal unitPrice;
    private Boolean isActive;
}
