package com.io.usos.controllers;

import com.io.usos.models.Student;
import com.io.usos.services.DokumentyService;
import com.io.usos.services.SemestrService;
import com.io.usos.services.UserService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PracownicyController {

    protected final Log log = LogFactory.getLog(getClass());

    @Autowired
    DokumentyService dokumentyService;

    @Autowired
    UserService userService;

    @Autowired
    SemestrService semestrService;

    @GetMapping(value = "pokazPracownikow")
    public String showMyStypendium(Model model) {
        model.addAttribute("pracownicy", userService.getAllPracownik());
        return "pracownikList";
    }
}
