package com.finaninfo.contrlloer;

import com.finaninfo.service.AfficheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AfficheController {
    @Autowired
    private AfficheService afficheService;

    @RequestMapping("/affiche/{page}")
    public String affichePage(Model model, @PathVariable int page) {
        Pageable pageable = new PageRequest(page-1, 15);
        model.addAttribute("affiche" ,afficheService.findAfficheByPage(pageable));
        return "affiche";
    }
}
