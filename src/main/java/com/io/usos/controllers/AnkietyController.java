package com.io.usos.controllers;

import com.io.usos.models.Ankieta;
import com.io.usos.models.AnkietaOdpowiedz;
import com.io.usos.models.Odpowiedz;
import com.io.usos.models.Student;
import com.io.usos.services.DokumentyService;
import com.io.usos.services.SemestrService;
import com.io.usos.services.UserService;
import javassist.NotFoundException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class AnkietyController {

    protected final Log log = LogFactory.getLog(getClass());

    @Autowired
    DokumentyService dokumentyService;

    @Autowired
    UserService userService;

    @Autowired
    SemestrService semestrService;

    @GetMapping(value = "pokazAnkiety")
    public String showAllAnkiety(Model model) {
        model.addAttribute("ankietyAll", dokumentyService.getAllAnkieta());
        return "ankietaListAdmin";
    }

    @GetMapping(value = "pokazMojeAnkiety")
    public String showMyAnkiety(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Student student = userService.getStudent(auth.getName());

        model.addAttribute("ankiety", dokumentyService.getAllMyAnkieta(student.getId()));
        log.info(dokumentyService.getAllMyAnkieta(student.getId()).size());
        return "ankietaList";
    }

    @GetMapping(value = "zobaczWyniki")
    public String showAnkietaResults(Model model, int id) throws NotFoundException {

        List<AnkietaOdpowiedz> odp = dokumentyService.getAllOdpowiedzi(id);
        List<Float> srednie = new ArrayList<>();
        float temp = 0;
        for (int i = 0; i < odp.size(); i++) {
            temp += odp.get(i).Srednia();
        }
        float srednia = temp / odp.size();
        for (int i = 0; i < dokumentyService.getAnkieta(id).getAnkietaPytanie().size(); i++) {
            int tmp = 0;
            for (int j = 0; j < odp.size(); j++) {
                tmp += odp.get(j).getOdpowiedzi().get(i).getValue();
            }
            srednie.add((float) (tmp/odp.size()));
        }

        model.addAttribute("ankieta", dokumentyService.getAnkieta(id));
        model.addAttribute("wyniki", dokumentyService.getAllOdpowiedzi(id));
        model.addAttribute("srednia", srednia);
        model.addAttribute("srednie", srednie);

        return "ankietaResults";
    }

    @GetMapping(value = "wypelnijAnkiete")
    public String fillAnkieta(Model model, int id) throws NotFoundException {

        Ankieta a = dokumentyService.getAnkieta(id);
        AnkietaOdpowiedz ao = new AnkietaOdpowiedz();
        List<Odpowiedz> odp = dokumentyService.getAllOdpowiedz();
        model.addAttribute("odpowiedz", ao);
        model.addAttribute("ankieta", a);
        return "ankietaForm";
    }

    @RequestMapping(value = "wypelnijAnkiete", method = RequestMethod.POST)
    public String registration(@ModelAttribute("odpowiedz") AnkietaOdpowiedz ankietaOdpowiedz, @RequestParam String aid) throws NotFoundException {

        Ankieta a = dokumentyService.getAnkieta(Integer.parseInt(aid));
        ankietaOdpowiedz.setAnkieta(a);
        dokumentyService.saveAnkietaOdpowiedz(ankietaOdpowiedz);
        return "redirect:home";
    }
}
