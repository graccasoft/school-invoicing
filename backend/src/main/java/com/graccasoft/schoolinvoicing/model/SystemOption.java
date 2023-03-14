package com.graccasoft.schoolinvoicing.model;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class SystemOption extends BaseEntity {
    private String name;
    private String value;

    public SystemOption(String name, String value) {
        this.name = name;
        this.value = value;
    }
}
