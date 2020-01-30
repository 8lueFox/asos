package com.io.usos.repositories;

import com.io.usos.models.AnkietaPytanie;
import com.io.usos.models.Odpowiedz;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OdpowiedzRepository extends JpaRepository<Odpowiedz, Integer> {

}
