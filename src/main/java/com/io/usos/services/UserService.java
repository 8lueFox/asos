package com.io.usos.services;

import com.io.usos.models.*;
import javassist.NotFoundException;

import java.util.List;

public interface UserService {

    Pracownik getPracownik(int id);

    List<Pracownik> getAllPracownik();

    void deletePracownik(int id);

    void savePracownik(Pracownik pracownik);

    Student getStudent(int id);

    List<Student> getAllStudent();

    void deleteStudent(int id);

    void saveStudent(Student student);

}
