package com.io.usos.controllers;

import com.io.usos.models.Pracownik;
import com.io.usos.models.Student;
import com.io.usos.models.Stypendium;
import com.io.usos.services.DokumentyService;
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

    @GetMapping(value = "utworzWniosek")
    public String createStypendium(Model model, Optional<Integer> cid) {

        model.addAttribute("stypendium",
                cid.isPresent() ?
                        dokumentyService.getStypendium(cid.get()) :
                        new Stypendium());

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
        model.addAttribute("stypendiaAdminOld", dokumentyService.getAllStypendiumOld());
        return "stypendiumListAdmin";
    }

    @RequestMapping(value = "stypendiumForm.html", method = RequestMethod.POST)
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
