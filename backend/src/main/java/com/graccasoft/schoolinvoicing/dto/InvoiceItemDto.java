package com.graccasoft.schoolinvoicing.dto;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class InvoiceItemDto {
    private Long billableId;
    private String billableDescription;
    private Integer quantity;
    private BigDecimal unitPrice;
}
