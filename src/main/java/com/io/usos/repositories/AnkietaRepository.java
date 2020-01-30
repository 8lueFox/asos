package com.io.usos.repositories;

import com.io.usos.models.Ankieta;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AnkietaRepository extends JpaRepository<Ankieta, Integer> {

    List<Ankieta> findDistinctByPrzedmiot_StudenciId(int id);
}
