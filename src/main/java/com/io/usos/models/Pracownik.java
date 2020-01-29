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
@Table(name = "pracownicy")
public class Pracownik {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank
    private String imie;

    @NotBlank
    private String nazwisko;

    private boolean pracownikAdministracyjny;

    @ManyToMany(fetch = FetchType.EAGER)
    private List<Przedmiot> przedmiotList;

    public Pracownik(String imie, String nazwisko, boolean pracownikAdministracyjny){
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.pracownikAdministracyjny = pracownikAdministracyjny;
        this.przedmiotList = new ArrayList<>();
    }

    public void dodajPrzedmiot(Przedmiot przedmiot){
        przedmiotList.add(przedmiot);
    }

    public void usunPrzedmiot(Przedmiot przedmiot) {
        if(przedmiotList.contains(przedmiot)){
            przedmiotList.remove(przedmiot);
        }
    }
}
