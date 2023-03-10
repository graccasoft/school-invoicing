package com.graccasoft.schoolinvoicing.dto;

import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StudentStatementDto {
    private Long studentId;
    private BigDecimal balance;
    private List<StudentStatementItemDto> items;
}
