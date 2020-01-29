package com.io.usos.repositories;

import com.io.usos.models.Stypendium;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface StypendiumRepository extends JpaRepository<Stypendium, Integer> {

    List<Stypendium> findAllByStudent_Id(int id);
}
