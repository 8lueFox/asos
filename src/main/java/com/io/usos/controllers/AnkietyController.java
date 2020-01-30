package com.io.usos.controllers;

import com.io.usos.models.Ankieta;
import com.io.usos.models.AnkietaOdpowiedz;
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
import org.springframework.web.bind.annotation.GetMapping;

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
        return "ankietaList";
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
        model.addAttribute("ankieta", dokumentyService.getAnkieta(id));
        model.addAttribute("wyniki", dokumentyService.getAllOdpowiedzi(id));
        return "ankietaResults";
    }

    @GetMapping(value = "wypelnijAnkiete")
    public String fillAnkieta(Model model, int id) throws NotFoundException {

        Ankieta a = dokumentyService.getAnkieta(id);
        AnkietaOdpowiedz ao = new AnkietaOdpowiedz();
        model.addAttribute("odpowiedz",ao);
        model.addAttribute("ankieta", a);
        return "ankietaForm";
    }
}
