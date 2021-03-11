package be.thomasmore.party.controllers;

import be.thomasmore.party.repositories.PartyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PartyController {


    @Autowired
    private PartyRepository partyRepository;

    @GetMapping({"/party"})
    public String party(Model model) {
        return "partydetails";
    }
}
