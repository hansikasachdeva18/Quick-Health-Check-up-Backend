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
public class SelectedSymptoms {
    private final List<String> selectedSymptoms;
    private final String name;
}
