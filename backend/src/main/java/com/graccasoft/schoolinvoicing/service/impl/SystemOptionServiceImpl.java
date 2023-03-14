package com.graccasoft.schoolinvoicing.service.impl;

import com.graccasoft.schoolinvoicing.model.SystemOption;
import com.graccasoft.schoolinvoicing.repository.SystemOptionRepository;
import com.graccasoft.schoolinvoicing.service.SystemOptionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SystemOptionServiceImpl implements SystemOptionService {
    private final SystemOptionRepository systemOptionRepository;

    public SystemOptionServiceImpl(SystemOptionRepository systemOptionRepository) {
        this.systemOptionRepository = systemOptionRepository;
    }

    @Override
    public SystemOption getSystemOption(String name) {
        return systemOptionRepository.findFirstByName(name).orElse(null) ;
    }

    @Override
    @Transactional
    public SystemOption saveSystemOption(String name, String value) {
        SystemOption systemOption = getSystemOption(name);
        if(systemOption == null) {
            systemOption = new SystemOption(name, value);
        }else{
            systemOption.setValue(value);
        }

        return systemOptionRepository.save(systemOption);
    }
}
