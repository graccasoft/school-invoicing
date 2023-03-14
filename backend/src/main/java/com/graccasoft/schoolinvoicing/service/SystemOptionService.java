package com.graccasoft.schoolinvoicing.service;

import com.graccasoft.schoolinvoicing.model.SystemOption;

public interface SystemOptionService {
    SystemOption getSystemOption(String name);
    SystemOption saveSystemOption(String name, String value);
}
