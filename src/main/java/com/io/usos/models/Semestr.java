package com.io.usos.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "semestry")
public class Semestr {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Instant dataRozpoczecia;

    private Instant dataZakonczenia;

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Przedmiot> przedmioty;

    public Semestr(Instant dataRozpoczecia, Instant dataZakonczenia){
        this.dataRozpoczecia = dataRozpoczecia;
        this.dataZakonczenia = dataZakonczenia;
        this.przedmioty = new HashSet<>();
    }

    @Override
    public String toString(){
        String string;
        ZonedDateTime zonedDateTime = dataRozpoczecia.atZone(ZoneId.of("Poland"));
        if(zonedDateTime.getMonth().getValue() <= 2){
            string = "Semestr letni " + zonedDateTime.getYear() + "/" + zonedDateTime.getYear();
        }else{
            string = "Semestr zimowy " + zonedDateTime.getYear() + "/" + (zonedDateTime.getYear()+1);
        }
        return string;
    }

    public void dodajPrzedmiot(Przedmiot przedmiot){
        przedmioty.add(przedmiot);
    }

}
