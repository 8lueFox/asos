package com.io.usos.repositories;

import com.io.usos.models.Semestr;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SemestRepository extends JpaRepository<Semestr, Integer> {
}
