package com.io.usos.controllers;

import com.io.usos.models.Pracownik;
import com.io.usos.models.Przedmiot;
import com.io.usos.models.Role;
import com.io.usos.models.Student;
import com.io.usos.repositories.RoleRepository;
import com.io.usos.services.DokumentyService;
import com.io.usos.services.SemestrService;
import com.io.usos.services.UserService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@Controller
public class PracownicyController {

    protected final Log log = LogFactory.getLog(getClass());

    @Autowired
    DokumentyService dokumentyService;

    @Autowired
    UserService userService;

    @Autowired
    SemestrService semestrService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    RoleRepository roleRepository;

    @GetMapping(value = "pokazPracownikow")
    public String showAllEmployees(Model model) {
        model.addAttribute("pracownicy", userService.getAllPracownik());
        return "pracownikList";
    }


    @GetMapping(value = "pracownikSzczegoly")
    public String showEmployee(Model model, int id) {
        model.addAttribute("pracownik", userService.getPracownik(id));
        model.addAttribute("wszystkiePrzedmioty", semestrService.getAllPrzedmiot());
        return "pracownikDetails";
    }

    @GetMapping(value = "usunPrzedmiotOdPracownika")
    public String showEmployee(Model model, int id,int pid) {
        Przedmiot p = semestrService.getPrzedmiot(id);
        Pracownik pr = userService.getPracownik(pid);
        pr.usunPrzedmiot(p);
        userService.savePracownik(pr);
        return "redirect:pracownikSzczegoly?id=" + pid;
    }

    @GetMapping(value = "edytujPracownika")
    public String editEmployee(Model model, int id) {

        model.addAttribute("pracownik",userService.getPracownik(id));

        return "pracownikEditForm";
    }

    @RequestMapping(value = "pracownikAddPrzedmiot", method = RequestMethod.POST)
    //@ResponseStatus(HttpStatus.CREATED)
    public String addPrzedmiotToEmployee(@RequestParam String id, @RequestParam String przedmiot) {

        Przedmiot p = semestrService.getPrzedmiot(Integer.valueOf(przedmiot));
        Pracownik pr = userService.getPracownik(Integer.valueOf(id));
        pr.dodajPrzedmiot(p);
        userService.savePracownik(pr);

        return "redirect:pracownikSzczegoly?id=" + id;//po udanym dodaniu/edycji przekierowujemy na listę
    }

    @GetMapping("pracownikForm")
    public String registration(Model model) {
        model.addAttribute("userCommand", new Pracownik());
        return "pracownikForm";
    }

    @RequestMapping(value = "pracownikForm", method = RequestMethod.POST)
    public String registration(@ModelAttribute("userCommand") Pracownik userForm) {

        Role userRole;
        if(userForm.isPracownikAdministracyjny()){
            userRole = roleRepository.findRoleByType(Role.Types.Pracownik_Dziekanatu);
        }else{
            userRole = roleRepository.findRoleByType(Role.Types.Pracownik);
        }
        log.info(userRole.getId());
        userForm.setRole(userRole);
        userForm.setEnabled(true);
        userForm.setPassword(passwordEncoder.encode(userForm.getPassword()));
        userForm.setPasswordConfirm(userForm.getPassword());;

        userService.savePracownik(userForm);
        return "redirect:pokazPracownikow";
    }

}
