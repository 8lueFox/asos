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
public class Pracownik extends User{

    private boolean pracownikAdministracyjny;

    @ManyToMany(fetch = FetchType.EAGER)
    private List<Przedmiot> przedmiotList;

    public Pracownik(String username, String password, String passwordConfirm, String imie, String nazwisko, Role role,boolean pracownikAdministracyjny){
        super(username, password,passwordConfirm, imie, nazwisko, role);
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
