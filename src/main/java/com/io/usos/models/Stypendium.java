package com.io.usos.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;
import java.time.Instant;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "stypendia")
public class Stypendium {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Instant dataZlozenia;

    @PositiveOrZero
    private float sredniaOcen;

    @PositiveOrZero
    private int punkty;

    @Size(min = 0, max = 1000)
    private String informacje;

    private boolean zatwierdzony;

    @OneToOne(fetch = FetchType.EAGER)
    private Student student;

    public Stypendium(Student student, float sredniaOcen, String informacje){
        this.student = student;
        this.sredniaOcen = sredniaOcen;
        this.informacje = informacje;
        this.dataZlozenia = Instant.now();
    }

    public void calculateBasicPoints(){
        this.punkty = (int) sredniaOcen * 100;
    }

    public void editPointsAmount(int amount){
        if(amount == 0) return;
        punkty += amount;
    }
}
