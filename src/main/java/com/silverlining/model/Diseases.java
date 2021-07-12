package com.silverlining.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@NoArgsConstructor(force = true)
@RequiredArgsConstructor
@Getter
@Setter
@Entity
@Table(name="diseases")
public class Diseases {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "disease_id")
    private final Long id;
    @Column(name = "disease", length = 50)
    private final String disease;
    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
            name = "diseases_medicines",
            joinColumns = { @JoinColumn(name = "disease_id") },
            inverseJoinColumns = { @JoinColumn(name = "medicine_id") }
    )
    private final List<Medicine> medicines;
    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
            name = "diseases_symptoms",
            joinColumns = { @JoinColumn(name = "disease_id") },
            inverseJoinColumns = { @JoinColumn(name = "symptom_id") }
    )
    private final List<Symptoms> symptomsList;
    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
            name = "diseases_reports",
            joinColumns = { @JoinColumn(name = "disease_id") },
            inverseJoinColumns = { @JoinColumn(name = "report_id") }
    )
    private final List<Report> reportsTD;
}
