package com.io.usos.controllers;

import com.io.usos.models.Pracownik;
import com.io.usos.services.StudentListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class StudentListController {

    @Autowired
    StudentListService studentListService;

    @GetMapping(value = "pokazStudentow")
    public String getWorkerDetails(Model model){
        model.addAttribute("pracownik", studentListService.getPracownik());
        return "studentListWorkerDetails";
    }

    @PostMapping(value = "pokazStudentow")
    public String showYears(Model model, @Valid @ModelAttribute("pracownik")Pracownik pracownik){
        model.addAttribute("listaKierunkow", "studentListService.getYears()");
        return "studentListChooseYear";
    }
}
