package com.silverlining.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
@Table(name="medicine")
public class Medicine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "medicine_id")
    private final Long id;
    @Column(name = "name", length = 50)
    private final String name;
    @Column(name = "dosage_quantity", length = 50)
    private final String dosageQuantity;
    @Column(name = "dosage_schedule", length = 50)
    private final String dosageSchedule;
    @ManyToMany(mappedBy = "medicines")
    @JsonIgnore
    private final List<Diseases> diseases;
}
