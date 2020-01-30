package com.io.usos.controllers;

import com.io.usos.models.Przedmiot;
import com.io.usos.models.Student;
import com.io.usos.models.User;
import com.io.usos.repositories.SemestRepository;
import com.io.usos.services.SemestrService;
import com.io.usos.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ZapisyController {

    @Autowired
    SemestrService semestrService;

    @Autowired
    UserService userService;

    @GetMapping(value = "zapisDoPrzedmiotu")
    public String zapis(Model model){
        model.addAttribute("listaPrzedmiotow", semestrService.getAllPrzedmiot());
        return "zapisy";
    }

    @GetMapping(value = "zapisDoPrzedmiotu", params = "idPrzedmiotu")
    public String zapisz(Integer idPrzedmiotu){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails user = (UserDetails) authentication.getPrincipal();
        Student student = userService.getStudent(user.getUsername());
        Przedmiot przedmiot = semestrService.getPrzedmiot(idPrzedmiotu);
        przedmiot.addStudent(student);
        semestrService.savePrzedmiot(przedmiot);
        return "zapisySucess";
    }
}
