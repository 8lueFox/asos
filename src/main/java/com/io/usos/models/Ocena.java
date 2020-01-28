package com.io.usos.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Past;
import javax.validation.constraints.PositiveOrZero;
import java.time.Instant;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "oceny")
public class Ocena {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.EAGER)
    private Przedmiot przedmiot;

    @ManyToOne
    private Student student;

    @PositiveOrZero
    @Max(5)
    private float ocena;

    private Instant dataWstawienia;

    public Ocena(float ocena, Student student, Przedmiot przedmiot){
        this.ocena = ocena;
        this.student = student;
        this.przedmiot = przedmiot;
        this.dataWstawienia = Instant.now();
    }
}
