package com.io.usos.repositories;

import com.io.usos.models.Ankieta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnkietaRepository extends JpaRepository<Ankieta, Integer> {
}
