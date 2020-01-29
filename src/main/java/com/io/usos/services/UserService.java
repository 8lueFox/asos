package com.io.usos.services;

import com.io.usos.models.*;
import javassist.NotFoundException;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {

    Pracownik getPracownik(int id);

    Pracownik getPracownik(String username);

    List<Pracownik> getAllPracownik();

    void deletePracownik(int id);

    void savePracownik(Pracownik pracownik);

    Student getStudent(int id);

    Student getStudent(String username);

    List<Student> getAllStudent();

    void deleteStudent(int id);

    void saveStudent(Student student);

    boolean isUniqueLogin(String login);


}
