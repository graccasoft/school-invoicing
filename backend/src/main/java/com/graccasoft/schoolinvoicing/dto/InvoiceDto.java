package com.graccasoft.schoolinvoicing.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Builder
public class InvoiceDto {
    private Long id;
    private Date createdAt;
    private Long studentId;
    private String studentName;
    private Long schoolClassId;
    private String schoolClassName;
    private BigDecimal totalAmount;
    private List<InvoiceItemDto> items;
}
