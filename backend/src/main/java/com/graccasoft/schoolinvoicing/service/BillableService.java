package com.graccasoft.schoolinvoicing.service;

import com.graccasoft.schoolinvoicing.model.Billable;

import java.util.List;

public interface BillableService {
    Billable saveBillable(Billable billable);
    Billable getBillable(Long billableId);
    List<Billable> getBillableItemsByClass(Long schoolClassId);
}
