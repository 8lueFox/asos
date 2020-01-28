package com.io.usos.services;

import com.io.usos.models.Pracownik;
import com.io.usos.repositories.PracownikRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentListServiceImpl implements StudentListService{

    @Autowired
    PracownikRepository pracownikRepository;

    @Override
    public Pracownik getPracownik() {
        return new Pracownik();
    }
}
