package com.io.usos.services;

import com.io.usos.exceptions.ObjectNotFoundException;
import com.io.usos.models.Ankieta;
import com.io.usos.models.AnkietaOdpowiedz;
import com.io.usos.models.Odpowiedz;
import com.io.usos.models.Stypendium;
import com.io.usos.repositories.*;
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
    AnkietaOdpowiedzRepository ankietaOdpowiedzRepository;
    @Autowired
    AnkietaPytanieRepository ankietaPytanieRepository;
    @Autowired
    OdpowiedzRepository odpowiedzRepository;

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
    public List<Odpowiedz> getAllOdpowiedz() {
        return odpowiedzRepository.findAll();
    }

    @Override
    public List<AnkietaOdpowiedz> getAllOdpowiedzi() {
        return ankietaOdpowiedzRepository.findAll();
    }

    @Override
    public List<AnkietaOdpowiedz> getAllOdpowiedzi(int id) {
        return ankietaOdpowiedzRepository.findAllByAnkietaId(id);
    }

    @Override
    public List<Ankieta> getAllMyAnkieta(int id) {
        return ankietaRepository.findDistinctByPrzedmiot_StudenciId(id);
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
    public void saveAnkietaOdpowiedz(AnkietaOdpowiedz ankietaOdpowiedz) {
        ankietaOdpowiedzRepository.save(ankietaOdpowiedz);
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
    public List<Stypendium> getAllStypendiumOld() {
        return stypendiumRepository.findAllByZatwierdzonyIsTrue();
    }

    @Override
    public List<Stypendium> getAllMyStypendium(int id) {
        return stypendiumRepository.findAllByStudent_Id(id);
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
