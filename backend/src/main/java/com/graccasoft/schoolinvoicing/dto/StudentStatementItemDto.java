package com.graccasoft.schoolinvoicing.dto;

import com.graccasoft.schoolinvoicing.enums.TransactionType;
import lombok.*;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StudentStatementItemDto {
    private TransactionType transactionType;
    private String description;
    private Date date;
    private BigDecimal amount;
}
