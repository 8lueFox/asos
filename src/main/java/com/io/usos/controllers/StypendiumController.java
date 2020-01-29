package com.io.usos.controllers;

import com.io.usos.models.Stypendium;
import com.io.usos.services.DokumentyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class StypendiumController {

    @Autowired
    DokumentyService dokumentyService;

    @GetMapping(value = "utworzWniosek")
    public String createStypendium(Model model){
        model.addAttribute("stypendium", new Stypendium());
        return "stypendiumForm";
    }

    // do post maping for : stypendiumForm

    @GetMapping(value = "pokazWnioski")
    public String showMyStypendium(Model model){
        model.addAttribute("stypendia", dokumentyService.getAllMyStypendium(1));
        return "stypendiumList";
    }

    @GetMapping(value = "zarzadzajWnioskami")
    public String manageStypendium(Model model){
        model.addAttribute("stypendia", dokumentyService.getAllStypendium());
        return "stypendiumListAdmin";
    }
}
