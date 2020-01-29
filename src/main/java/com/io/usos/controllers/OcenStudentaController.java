package com.io.usos.controllers;

import com.io.usos.models.Ocena;
import com.io.usos.models.Student;
import com.io.usos.services.SemestrService;
import com.io.usos.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class OcenStudentaController {

    @Autowired
    UserService userService;
    @Autowired
    SemestrService semestrService;

    @GetMapping(value = "wstawienieOcen")
    public String pokazPrzedmioty(Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails user = (UserDetails) authentication.getPrincipal();
        model.addAttribute("przedmiotLista", userService.getPrzedmiotyPracownika(user.getUsername()));
        return "studentListDoOcenyWybieraniePrzedmiotu";
    }

    @GetMapping(value = "wstawienieOcen", params = "idPrzedmiotu")
    public String pokazStudentowOceny(Model model, int idPrzedmiotu){
        List<Student> students = semestrService.getStudenciPrzedmiotu(idPrzedmiotu);
        List<Ocena> oceny = semestrService.getOcenyStudentówPrzedmiotu(idPrzedmiotu);
        students.sort(Comparator.comparing(Student::getId));
        model.addAttribute("listaStudentow", students);
        model.addAttribute("listaOcen", oceny);
        return "studentListDoOceny";
    }
}
