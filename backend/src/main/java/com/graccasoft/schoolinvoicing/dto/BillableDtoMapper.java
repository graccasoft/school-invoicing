package com.graccasoft.schoolinvoicing.dto;

import com.graccasoft.schoolinvoicing.model.Billable;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class BillableDtoMapper implements Function<Billable, BillableDto> {
    @Override
    public BillableDto apply(Billable billable) {
        return new BillableDto(
                billable.getId(),
                billable.getSchoolClass().getId(),
                billable.getDescription(),
                billable.getUnitPrice()
        ) ;
    }
}
