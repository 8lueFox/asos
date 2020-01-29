package com.io.usos.services;

import com.io.usos.models.Ankieta;
import com.io.usos.models.Stypendium;
import javassist.NotFoundException;

import java.util.List;

public interface DokumentyService {

    Ankieta getAnkieta(int id) throws NotFoundException;

    List<Ankieta> getAllAnkieta();

    void deleteAnkieta(int id);

    void saveAnkieta(Ankieta ankieta);

    Stypendium getStypendium(int id);

    List<Stypendium> getAllStypendium();

    List<Stypendium> getAllStypendiumOld();

    List<Stypendium> getAllMyStypendium(int id);

    void deleteStypendium(int id);

    void saveStypendium(Stypendium stypendium);

}
