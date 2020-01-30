package com.io.usos.repositories;

import com.io.usos.models.Rok;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RokRepository extends JpaRepository<Rok, Integer> {
    Rok findAllByNazwaKierunku(String nazwaKierunku);
}
