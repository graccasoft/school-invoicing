package com.graccasoft.schoolinvoicing.service.impl;

import com.graccasoft.schoolinvoicing.dto.BillableDto;
import com.graccasoft.schoolinvoicing.dto.BillableDtoMapper;
import com.graccasoft.schoolinvoicing.model.Billable;
import com.graccasoft.schoolinvoicing.repository.BillableRepository;
import com.graccasoft.schoolinvoicing.repository.SchoolClassRepository;
import com.graccasoft.schoolinvoicing.service.BillableService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BillableServiceImpl implements BillableService {

    private final BillableRepository billableRepository;
    private final BillableDtoMapper billableDtoMapper;
    private final SchoolClassRepository schoolClassRepository;

    public BillableServiceImpl(BillableRepository billableRepository, BillableDtoMapper billableDtoMapper,
                               SchoolClassRepository schoolClassRepository) {
        this.billableRepository = billableRepository;
        this.billableDtoMapper = billableDtoMapper;
        this.schoolClassRepository = schoolClassRepository;
    }

    @Override
    @Transactional
    public BillableDto saveBillable(BillableDto billableDto) {
        Billable billable = new Billable();
        if( billableDto.id() != null && billableDto.id() != 0 )
            billable = billableRepository.getReferenceById(billableDto.id());

        billable.setUnitPrice(billableDto.unitPrice());
        billable.setSchoolClass( schoolClassRepository.getReferenceById(billableDto.schoolClassId()) );
        billable.setDescription(billableDto.description());
        return billableDtoMapper.apply( billableRepository.save(billable) );
    }

    @Override
    public BillableDto getBillable(Long billableId) {
        Billable billable = billableRepository.findById(billableId)
                .orElseThrow(()-> new EntityNotFoundException("Billable not found"));
        return billableDtoMapper.apply(billable);
    }

    @Override
    public List<BillableDto> getBillableItemsByClass(Long schoolClassId) {
        return billableRepository.getAllBySchoolClassId(schoolClassId)
                .stream()
                .map(billableDtoMapper)
                .toList();
    }
}
