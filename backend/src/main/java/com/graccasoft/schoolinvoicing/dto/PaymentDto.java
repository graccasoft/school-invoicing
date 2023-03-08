package com.graccasoft.schoolinvoicing.dto;

import lombok.*;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PaymentDto {
    private Long id;
    private BigDecimal amount;
    private Long studentId;
    private Date createdAt;
}
