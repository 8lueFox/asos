package com.io.usos.services;

import com.io.usos.models.Ankieta;
import com.io.usos.models.AnkietaOdpowiedz;
import com.io.usos.models.Stypendium;
import javassist.NotFoundException;

import java.util.List;

public interface DokumentyService {

    Ankieta getAnkieta(int id) throws NotFoundException;

    List<Ankieta> getAllAnkieta();

    List<AnkietaOdpowiedz> getAllOdpowiedzi();

    List<AnkietaOdpowiedz> getAllOdpowiedzi(int id);

    List<Ankieta> getAllMyAnkieta(int id);

    void deleteAnkieta(int id);

    void saveAnkieta(Ankieta ankieta);

    Stypendium getStypendium(int id);

    List<Stypendium> getAllStypendium();

    List<Stypendium> getAllStypendiumOld();

    List<Stypendium> getAllMyStypendium(int id);

    void deleteStypendium(int id);

    void saveStypendium(Stypendium stypendium);

}
