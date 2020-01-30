package com.io.usos.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "ankiety")
public class Ankieta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.EAGER)
    private Pracownik pracownik;

    @ManyToOne(fetch = FetchType.EAGER)
    private Przedmiot przedmiot;

    @ManyToMany(fetch = FetchType.LAZY)
    private List<AnkietaPytanie> ankietaPytanie;

    public Ankieta(Pracownik pracownik, Przedmiot przedmiot, List<AnkietaPytanie> ankietaPytanie) {
        this.pracownik = pracownik;
        this.przedmiot = przedmiot;
        this.ankietaPytanie = ankietaPytanie;
    }
}
