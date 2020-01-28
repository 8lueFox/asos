package com.io.usos.repositories;

import com.io.usos.models.Przedmiot;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PrzedmiotRepository extends JpaRepository<Przedmiot, Integer> {
}
