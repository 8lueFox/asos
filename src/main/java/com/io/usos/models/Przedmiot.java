package com.io.usos.models;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "przedmioty")
public class Przedmiot {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank
    private String nazwa;

    @ManyToMany(fetch = FetchType.EAGER)
    List<Student> studenci;

    public Przedmiot(String nazwa){
        this.nazwa = nazwa;
        this.studenci = new ArrayList<>();
    }

    public void addStudent(Student student){
        studenci.add(student);
    }
}
