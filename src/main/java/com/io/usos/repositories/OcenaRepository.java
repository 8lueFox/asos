package com.io.usos.repositories;

import com.io.usos.models.Ocena;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface OcenaRepository extends JpaRepository<Ocena, Integer> {
    List<Ocena> findAllByPrzedmiotIdOrderByStudentId(Integer przedmiotId);
}
