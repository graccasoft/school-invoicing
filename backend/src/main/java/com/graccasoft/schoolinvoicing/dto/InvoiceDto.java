package com.graccasoft.schoolinvoicing.dto;

import lombok.*;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InvoiceDto {
    private Long id;
    private String title;
    private Date createdAt;
    private Long studentId;
    private String studentName;
    private Long schoolClassId;
    private String schoolClassName;
    private BigDecimal totalAmount;
    private List<InvoiceItemDto> items;
}
