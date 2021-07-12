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
public class ProbableDiseases {
    private final List<ProbableDisease> probableDiseases;
    private final Long userId;
}
