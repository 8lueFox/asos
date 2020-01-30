package com.io.usos.repositories;

import com.io.usos.models.Ankieta;
import com.io.usos.models.AnkietaOdpowiedz;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AnkietaOdpowiedzRepository extends JpaRepository<AnkietaOdpowiedz, Integer> {

    List<AnkietaOdpowiedz> findAllByAnkietaId(int id);
}
