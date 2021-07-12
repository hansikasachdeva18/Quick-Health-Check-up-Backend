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
@Table(name="symptoms")
public class Symptoms {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "symptom_id")
    private final Long id;
    @Column(name = "symptom", length = 50)
    private final String symptom;
    @Column(name = "type", length = 50)
    private final String type;
    @ManyToMany(mappedBy = "symptomsList")
    @JsonIgnore
    private final List<Diseases> diseases;
    @ManyToMany(mappedBy = "symptoms")
    @JsonIgnore
    private final List<User> users;
}
