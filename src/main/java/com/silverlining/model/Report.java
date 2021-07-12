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
@Table(name="report")
public class Report {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "report_id")
    private final Long id;
    @Column(name = "test_name", length = 50)
    private final String testName;
    @ManyToMany(mappedBy = "reportsToDo")
    @JsonIgnore
    private final List<User> users;
    @ManyToMany(mappedBy = "reportsTD")
    @JsonIgnore
    private final List<Diseases> diseases;
}
