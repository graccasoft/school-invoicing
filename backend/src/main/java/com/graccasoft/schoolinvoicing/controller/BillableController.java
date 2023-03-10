package com.graccasoft.schoolinvoicing.controller;

import com.graccasoft.schoolinvoicing.dto.BillableDto;
import com.graccasoft.schoolinvoicing.service.BillableService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("/billable")
public class BillableController {

    private final BillableService billableService;

    public BillableController(BillableService billableService) {
        this.billableService = billableService;
    }

    @PostMapping
    public BillableDto saveBillable(@RequestBody BillableDto billableDto){
        return this.billableService.saveBillable(billableDto);
    }

}
