package com.io.usos.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

import java.time.Instant;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "lata")
public class Rok {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank
    private String nazwaKierunku;

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Student> studenci;

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Semestr> semestry;

    private Instant dataRozpoczecia;

    private Instant dataZakonczenia;

    public Rok(String nazwaKierunku, Instant dataRozpoczecia, Instant dataZakonczenia){
        this.nazwaKierunku = nazwaKierunku;
        this.dataRozpoczecia = dataRozpoczecia;
        this.dataZakonczenia = dataZakonczenia;
        this.studenci = new HashSet<>();
        this.semestry = new HashSet<>();
    }

    public void dodajStudenta(Student student){
        studenci.add(student);
    }

    public void dodajSemestr(Semestr semestr){
        semestry.add(semestr);
    }

}