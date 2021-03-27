package be.thomasmore.party.controllers;

import be.thomasmore.party.model.Party;
import be.thomasmore.party.model.Venue;
import be.thomasmore.party.repositories.PartyRepository;
import be.thomasmore.party.repositories.VenueRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private Logger logger = LoggerFactory.getLogger(AdminController.class);

    @Autowired
    private PartyRepository partyRepository;

    @Autowired
    private VenueRepository venueRepository;

    @ModelAttribute("party")
    public Party findParty(@PathVariable Integer id) {
        logger.info("findParty " + id);
        Optional<Party> optionalParty = partyRepository.findById(id);
        if (optionalParty.isPresent())
            return optionalParty.get();
        return null;
    }

    @GetMapping("/partyedit/{id}")
    public String partyEdit(Model model,
                            @PathVariable int id) {
        logger.info("partyEdit " + id);
        model.addAttribute("venues", venueRepository.findAll());
        return "admin/partyedit";
    }

    @PostMapping("/partyedit/{id}")
    public String partyEditPost(Model model,
                                @PathVariable int id,
                                @ModelAttribute("party") Party party,
                                @RequestParam int venueId) {
        logger.info("partyEditPost " + id + " -- new name=" + party.getName());
        if ( party.getVenue().getId() != venueId) {
            party.setVenue(new Venue(venueId));
        }
        partyRepository.save(party);
        return "redirect:/partydetails/" + id;
    }

}
