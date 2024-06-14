package com.example.demo.dao;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class StudentSearchCriteria {
    private String name;
    private int minAge;
    private int maxAge;
    private String email;
    private String emailSuffix;
    private String namePrefix;
}

