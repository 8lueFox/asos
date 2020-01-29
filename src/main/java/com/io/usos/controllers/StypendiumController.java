package com.io.usos.controllers;

import com.io.usos.models.Pracownik;
import com.io.usos.models.Student;
import com.io.usos.models.Stypendium;
import com.io.usos.services.DokumentyService;
import com.io.usos.services.UserService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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

    //@Secured("Student")
    @GetMapping(value = "utworzWniosek")
    public String createStypendium(Model model, Optional<Integer> cid) {

        model.addAttribute("stypendium",
                cid.isPresent() ?
                        dokumentyService.getStypendium(cid.get()) :
                        new Stypendium());

        return "stypendiumForm";
    }

    //@Secured("Student")
    @GetMapping(value = "pokazWnioski")
    public String showMyStypendium(Model model) {
        model.addAttribute("stypendia", dokumentyService.getAllMyStypendium(1));
        return "stypendiumList";
    }

    //@Secured("Pracownik_Dziekanatu")
    @GetMapping(value = "zarzadzajWnioskami")
    public String manageStypendium(Model model) {
        model.addAttribute("stypendia", dokumentyService.getAllStypendium());
        return "stypendiumListAdmin";
    }

    //@Secured("Student")
    @RequestMapping(value = "stypendiumForm.html", method = RequestMethod.POST)
    //@ResponseStatus(HttpStatus.CREATED)
    public String processPizzaForm(@Valid @ModelAttribute("stypendium") Stypendium s) {

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
}
