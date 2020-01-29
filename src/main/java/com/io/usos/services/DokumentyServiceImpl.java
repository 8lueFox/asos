package com.io.usos.services;

import com.io.usos.exceptions.ObjectNotFoundException;
import com.io.usos.models.Ankieta;
import com.io.usos.models.Stypendium;
import com.io.usos.repositories.AnkietaRepository;
import com.io.usos.repositories.StypendiumRepository;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DokumentyServiceImpl implements DokumentyService {

    @Autowired
    AnkietaRepository ankietaRepository;

    @Autowired
    StypendiumRepository stypendiumRepository;

    @Override
    public Ankieta getAnkieta(int id) {
        Optional<Ankieta> optionalAnkieta = ankietaRepository.findById(id);
        Ankieta ankieta = optionalAnkieta.orElseThrow(() -> new ObjectNotFoundException("ankieta",id));
        return ankieta;
    }

    @Override
    public List<Ankieta> getAllAnkieta() {
        return ankietaRepository.findAll();
    }

    @Override
    public void deleteAnkieta(int id) {
        if (ankietaRepository.existsById(id) == true) {
            ankietaRepository.deleteById(id);
        }
    }

    @Override
    public void saveAnkieta(Ankieta ankieta) {
        ankietaRepository.save(ankieta);
    }

    @Override
    public Stypendium getStypendium(int id) {
        Optional<Stypendium> optionalStypendium = stypendiumRepository.findById(id);
        Stypendium stypendium = optionalStypendium.orElseThrow(() -> new ObjectNotFoundException("stypendium",id));
        return stypendium;
    }

    @Override
    public List<Stypendium> getAllStypendium() {
        return stypendiumRepository.findAll();
    }

    @Override
    public void deleteStypendium(int id) {
        if (stypendiumRepository.existsById(id) == true) {
            stypendiumRepository.deleteById(id);
        }
    }

    @Override
    public void saveStypendium(Stypendium stypendium) {
        stypendiumRepository.save(stypendium);
    }
}
