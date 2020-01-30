package com.io.usos.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "ankietaOdpowiedzi")
public class AnkietaOdpowiedz {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.EAGER)
    private Ankieta ankieta;

    @ManyToMany(fetch = FetchType.LAZY)
    private List<Odpowiedz> odpowiedzi;

    public AnkietaOdpowiedz(Ankieta ankieta, List<Odpowiedz> odpowiedzi){
        this.ankieta = ankieta;
        this.odpowiedzi = odpowiedzi;
    }

    public float Srednia(){
        float temp = 0;
        for(int i=0;i<odpowiedzi.size();i++){
            temp+=odpowiedzi.get(i).getValue();
        }
        return temp/odpowiedzi.size();
    }
}
