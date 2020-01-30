package com.io.usos.controllers;

import com.io.usos.models.Rok;
import com.io.usos.models.Semestr;
import com.io.usos.services.SemestrService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

@Controller
public class SemestrController {

    @Autowired
    SemestrService semestrService;

    @GetMapping(value = "utworzSemestr")
    public String utworzSemestr(Model model){
        model.addAttribute("rokList", semestrService.getAllRok());
        return "semestrForm";
    }

    @PostMapping(value = "utworzSemestr")
    public String zapiszSemestr(@RequestParam String nazwaKierunku, @RequestParam String dataRozpoczecia, @RequestParam String dataZakonczenia){
        Rok rok = semestrService.getRokByNazwaKierunku(nazwaKierunku);
        Semestr semestr = new Semestr();
        dataRozpoczecia = dataRozpoczecia+"T10:15:30.00Z";
        dataZakonczenia = dataZakonczenia+"T10:15:30.00Z";
        Instant dataR = Instant.parse(dataRozpoczecia);
        Instant dataZ = Instant.parse(dataZakonczenia);
        semestr.setDataZakonczenia(dataZ);
        semestr.setDataRozpoczecia(dataR);
        rok.dodajSemestr(semestr);
        semestrService.saveSemestr(semestr);
        semestrService.saveRok(rok);
        return "redirect:home";
    }
}
