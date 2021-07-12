package com.silverlining.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor(force = true)
@RequiredArgsConstructor
@Getter
@Setter
public class DiseaseSummary {
    private final String disease;
    private final List<Report> reports;
    private final List<Medicine> medicines;
    private final String reportLink;
    private final String reportPassword;
}
