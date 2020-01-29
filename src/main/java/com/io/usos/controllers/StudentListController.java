package com.io.usos.controllers;

import com.io.usos.models.Pracownik;
import com.io.usos.models.User;
import com.io.usos.services.SemestrService;
import com.io.usos.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class StudentListController {

    @Autowired
    UserService userService;
    @Autowired
    SemestrService semestrService;

    @GetMapping(value = "pokazStudentow")
    public String getPrzedmioty(Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails user = (UserDetails) authentication.getPrincipal();
        model.addAttribute("listaPrzedmiotow", userService.getPrzedmiotyPracownika(user.getUsername()));
        return "studentListChooseYear";
    }

    @GetMapping(value = "pokazStudentow", params = "idPrzedmiotu")
    public String getStudenci(Model model, Integer idPrzedmiotu){
        model.addAttribute("listaStudentow", semestrService.getStudenciPrzedmiotu(idPrzedmiotu));
        return "studentList";
    }

}
