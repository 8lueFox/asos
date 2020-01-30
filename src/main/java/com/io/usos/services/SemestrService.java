package com.io.usos.services;

import com.io.usos.models.*;

import java.util.List;

public interface SemestrService {

    Przedmiot getPrzedmiot(int id);

    List<Przedmiot> getAllPrzedmiot();

    List<Student> getStudenciPrzedmiotu(int  idPrzedmiotu);
    List<Ocena> getOcenyStudentówPrzedmiotu(int idPrzedmiotu);

    void deletePrzedmiot(int id);

    void savePrzedmiot(Przedmiot przedmiot);

    Rok getRok(int id);

    Rok getRokByNazwaKierunku(String nazwaKierunku);

    List<Rok> getAllRok();

    void deleteRok(int id);

    void saveRok(Rok rok);

    Ocena getOcena(int id);

    List<Ocena> getAllOcena();

    void deleteOcena(int id);

    void saveOcena(Ocena ocena);

    void saveSemestr(Semestr semestr);

}
