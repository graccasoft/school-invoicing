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
public class InvoiceItem extends BaseEntity {
    @ManyToOne
    private Invoice invoice;

    @ManyToOne
    @JoinColumn(name = "billable_id")
    private Billable billable;
    private Integer quantity;
    private BigDecimal unitPrice;
}
