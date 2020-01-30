package com.io.usos.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Student extends User{

    @NotBlank
    private String pesel;

    public Student(String username, String password, String passwordConfirm, String imie, String nazwisko,Role role, String pesel){
        super(username,password,passwordConfirm,imie,nazwisko, role);
        this.pesel = pesel;
    }

    @Override
    public String toString() {
        return getImie() + " " + getNazwisko() ;
    }
}
