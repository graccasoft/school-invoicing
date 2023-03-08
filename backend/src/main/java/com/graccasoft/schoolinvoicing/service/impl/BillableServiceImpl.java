package com.graccasoft.schoolinvoicing.service.impl;

import com.graccasoft.schoolinvoicing.model.Billable;
import com.graccasoft.schoolinvoicing.repository.BillableRepository;
import com.graccasoft.schoolinvoicing.service.BillableService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BillableServiceImpl implements BillableService {

    private final BillableRepository billableRepository;

    public BillableServiceImpl(BillableRepository billableRepository) {
        this.billableRepository = billableRepository;
    }

    @Override
    @Transactional
    public Billable saveBillable(Billable billable) {

        return billableRepository.save(billable) ;
    }

    @Override
    public Billable getBillable(Long billableId) {
        return billableRepository.findById(billableId)
                .orElseThrow(()-> new EntityNotFoundException("Billable not found"));
    }

    @Override
    public List<Billable> getBillableItemsByClass(Long schoolClassId) {
        return billableRepository.getAllBySchoolClassId(schoolClassId);
    }
}
