package com.io.usos.services;

import com.io.usos.exceptions.ObjectNotFoundException;
import com.io.usos.models.*;
import com.io.usos.repositories.*;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {


    @Autowired
    PracownikRepository pracownikRepository;


    @Autowired
    StudentRepository studentRepository;


    @Override
    public Pracownik getPracownik(int id) {
        Optional<Pracownik> optionalPracownik = pracownikRepository.findById(id);
        Pracownik pracownik = optionalPracownik.orElseThrow(() -> new ObjectNotFoundException("pracownik",id));
        return pracownik;
    }

    @Override
    public List<Pracownik> getAllPracownik() {
        return pracownikRepository.findAll();
    }

    @Override
    public void deletePracownik(int id) {
        if (pracownikRepository.existsById(id) == true) {
            pracownikRepository.deleteById(id);
        }
    }

    @Override
    public void savePracownik(Pracownik pracownik) {
        pracownikRepository.save(pracownik);
    }


    @Override
    public Student getStudent(int id) {
        Optional<Student> optionalStudent = studentRepository.findById(id);
        Student student = optionalStudent.orElseThrow(() -> new ObjectNotFoundException("student",id));
        return student;
    }

    @Override
    public List<Student> getAllStudent() {
        return studentRepository.findAll();
    }

    @Override
    public void deleteStudent(int id) {
        if (studentRepository.existsById(id) == true) {
            studentRepository.deleteById(id);
        }
    }

    @Override
    public void saveStudent(Student student) {
        studentRepository.save(student);
    }


}
