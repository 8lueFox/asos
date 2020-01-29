package com.io.usos.repositories;

import com.io.usos.models.Pracownik;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PracownikRepository extends JpaRepository<Pracownik, Integer> {
    Pracownik findByUsername(String username);
}
