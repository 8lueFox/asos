package com.io.usos.services;

import com.io.usos.config.ProfileNames;
import com.io.usos.exceptions.ObjectNotFoundException;
import com.io.usos.models.*;
import com.io.usos.repositories.*;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
@Profile(ProfileNames.DATABASE)
public class UserServiceImpl implements UserService {


    @Autowired
    PracownikRepository pracownikRepository;


    @Autowired
    StudentRepository studentRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    UserRepository userRepository;


    @Override
    public Pracownik getPracownik(int id) {
        Optional<Pracownik> optionalPracownik = Optional.ofNullable(pracownikRepository.findDistinctById(id));
        Pracownik pracownik = optionalPracownik.orElseThrow(() -> new ObjectNotFoundException("pracownik",id));
        return pracownik;
    }

    @Override
    public Pracownik getPracownik(String username) {
        Optional<Pracownik> optionalPracownik = Optional.ofNullable(pracownikRepository.findByUsername(username));
        Pracownik pracownik = optionalPracownik.orElseThrow(() -> new ObjectNotFoundException("pracownik",username));
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
    public Student getStudent(String username) {
        Optional<Student> optionalStudent = Optional.ofNullable(studentRepository.findByUsername(username));
        Student student = optionalStudent.orElseThrow(() -> new ObjectNotFoundException("student",username));
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

    @Override
    public boolean isUniqueLogin(String login) {
        return false;
    }

    @Override
    public List<Przedmiot> getPrzedmiotyPracownika(String username) {
        Pracownik pracownik = userRepository.findPracownikByUsername(username);
        return pracownik.getPrzedmiotList();
    }


    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if(user == null){
            System.out.println("Exception");
        }

        return createUserDetails(user);
    }

    private UserDetails createUserDetails(  User user){
        Set<GrantedAuthority> grantedAuthorities =
                user.getRoles().stream().map(//mapowanie Role na GrantedAuthority
                        r -> new SimpleGrantedAuthority(r.getType().toString())
                ).collect(Collectors.toSet());
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), user.isEnabled(), true, true, true, grantedAuthorities);
    }


}
