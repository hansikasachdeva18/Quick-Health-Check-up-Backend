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
@Table(name="user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private final Long id;
    @Column(name = "name", length = 50)
    private final String name;
    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
            name = "user_symptoms",
            joinColumns = { @JoinColumn(name = "user_id") },
            inverseJoinColumns = { @JoinColumn(name = "symptom_id") }
    )
    private final List<Symptoms> symptoms;
    @Column(name = "disease", length = 50)
    private final String disease;
    @Column(name = "link", length = 50)
    private final String link;
    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
            name = "user_reports",
            joinColumns = { @JoinColumn(name = "user_id") },
            inverseJoinColumns = { @JoinColumn(name = "report_id") }
    )
    private final List<Report> reportsToDo;
    @Column(name = "password", length = 50)
    private final String password;
}
