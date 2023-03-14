package com.graccasoft.schoolinvoicing.service;

import com.graccasoft.schoolinvoicing.dto.BillableDto;
import com.graccasoft.schoolinvoicing.model.Billable;

import java.util.List;

public interface BillableService {
    BillableDto saveBillable(BillableDto billableDto);
    BillableDto getBillable(Long billableId);
    List<BillableDto> getBillableItemsByClass(Long schoolClassId);
}
