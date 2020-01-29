package com.io.usos.services;

import com.io.usos.models.Ocena;
import com.io.usos.models.Przedmiot;
import com.io.usos.models.Rok;

import java.util.List;

public interface SemestrService {

    Przedmiot getPrzedmiot(int id);

    List<Przedmiot> getAllPrzedmiot();

    void deletePrzedmiot(int id);

    void savePrzedmiot(Przedmiot przedmiot);

    Rok getRok(int id);

    List<Rok> getAllRok();

    void deleteRok(int id);

    void saveRok(Rok rok);

    Ocena getOcena(int id);

    List<Ocena> getAllOcena();

    void deleteOcena(int id);

    void saveOcena(Ocena ocena);

    float getSredniaOcen(int id);

}
