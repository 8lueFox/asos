package com.io.usos.repositories;

import com.io.usos.models.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Integer> {
    Student findByUsername(String username);
}
