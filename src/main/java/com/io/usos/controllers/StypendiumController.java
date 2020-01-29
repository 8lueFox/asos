package com.io.usos.controllers;

import com.io.usos.models.Pracownik;
import com.io.usos.models.Student;
import com.io.usos.models.Stypendium;
import com.io.usos.services.DokumentyService;
import com.io.usos.services.SemestrService;
import com.io.usos.services.UserService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Controller
public class StypendiumController {

    protected final Log log = LogFactory.getLog(getClass());

    @Autowired
    DokumentyService dokumentyService;

    @Autowired
    UserService userService;

    @Autowired
    SemestrService semestrService;

    @GetMapping(value = "utworzWniosek")
    public String createStypendium(Model model, Optional<Integer> id) {

        Stypendium s = new Stypendium();
        if(id.isPresent()){
            s = dokumentyService.getStypendium(id.get());
        }
        else
        {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            Student student = userService.getStudent(auth.getName());

            s.setSredniaOcen(semestrService.getSredniaOcen(student.getId()));
        }

        model.addAttribute("stypendium",s);

        return "stypendiumForm";
    }

    @GetMapping(value = "pokazWnioski")
    public String showMyStypendium(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Student student = userService.getStudent(auth.getName());
        model.addAttribute("stypendia", dokumentyService.getAllMyStypendium(student.getId()));
        return "stypendiumList";
    }

    @GetMapping(value = "zarzadzajWnioskami")
    public String manageStypendium(Model model) {
        model.addAttribute("stypendiaAdmin", dokumentyService.getAllStypendium());
        return "stypendiumListAdmin";
    }

    @RequestMapping(value = "stypendiumForm.html", method = RequestMethod.POST)
    public String processStypendiumForm(@Valid @ModelAttribute("stypendium") Stypendium s) {

        /*if (errors.hasErrors()) {
            return "stypendiumForm";
        }*/
        s.setDataZlozenia(Instant.now());
        if (s.getStudent() == null) {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            Student student = userService.getStudent(auth.getName());
            s.setStudent(student);
        }
        dokumentyService.saveStypendium(s);

        return "redirect:pokazWnioski";//po udanym dodaniu/edycji przekierowujemy na listę
    }

    @GetMapping(value = "pokazStypendiumSzczegoly")
    public String showStypendiumDetails(Model model, int id) {
        model.addAttribute("stypendium", dokumentyService.getStypendium(id));
        return "stypendiumDetails";
    }

    @GetMapping(value = "zatwierdzStypendium")
    public String acceptStypendium(Model model, int id) {
        Stypendium s = dokumentyService.getStypendium(Integer.valueOf(id));
        s.setZatwierdzony(true);
        s.setRozpatrzonyPozytywnie(true);
        dokumentyService.saveStypendium(s);
        return "redirect:zarzadzajWnioskami";
    }

    @GetMapping(value = "odrzucStypendium")
    public String declineStypendium(Model model, int id) {
        Stypendium s = dokumentyService.getStypendium(Integer.valueOf(id));
        s.setZatwierdzony(true);
        s.setRozpatrzonyPozytywnie(false);
        dokumentyService.saveStypendium(s);
        return "redirect:zarzadzajWnioskami";
    }

    @RequestMapping(value = "dodajPunktyStypendium", method = RequestMethod.POST)
    //@ResponseStatus(HttpStatus.CREATED)
    public String addPointToStypendium(@RequestParam String id, @RequestParam String punkty) {

        Stypendium s = dokumentyService.getStypendium(Integer.valueOf(id));
        s.editPointsAmount(Integer.valueOf(punkty));
        dokumentyService.saveStypendium(s);

        return "redirect:pokazStypendiumSzczegoly?id=" + id;//po udanym dodaniu/edycji przekierowujemy na listę
    }

}
