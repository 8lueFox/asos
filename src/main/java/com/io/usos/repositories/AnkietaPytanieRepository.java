package com.io.usos.repositories;

import com.io.usos.models.Ankieta;
import com.io.usos.models.AnkietaPytanie;
import com.io.usos.models.AnkietaPytanie_;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AnkietaPytanieRepository extends JpaRepository<AnkietaPytanie, Integer> {

}
