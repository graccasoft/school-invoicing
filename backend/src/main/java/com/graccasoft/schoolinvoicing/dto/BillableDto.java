package com.graccasoft.schoolinvoicing.dto;

import java.math.BigDecimal;

public record BillableDto(Long id,
                          Long schoolClassId,
                          String description,
                          BigDecimal unitPrice) {
}
