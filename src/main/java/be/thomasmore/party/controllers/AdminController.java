package be.thomasmore.party.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AdminController {

    private Logger logger = LoggerFactory.getLogger(VenueController.class);

    @RequestMapping({"/admin"})
    public String admin(Model model) {
        return "admin";
    }
    @GetMapping({"partyedit/{id}"})
    public String partyedit(Model model) {
        return "partyedit";
    }

}
