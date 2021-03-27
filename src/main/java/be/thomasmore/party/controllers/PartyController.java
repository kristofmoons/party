package be.thomasmore.party.controllers;

import be.thomasmore.party.model.Party;
import be.thomasmore.party.repositories.PartyRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@Controller
public class PartyController {

    @Autowired
    private PartyRepository partyRepository;

    private Logger logger = LoggerFactory.getLogger(VenueController.class);

    @GetMapping("/partylist")
    public String partyList(Model model) {
        Iterable<Party> parties = partyRepository.findAll();
        model.addAttribute("parties", parties);
        return "partylist";
    }

    @GetMapping({"/partydetails", "partydetails/{id}"})
    public String partyDetails(Model model,
                               @PathVariable(required = false) Integer id) {


        Party party;

        Optional<Party> optionalParty = partyRepository.findById(id);
        if (optionalParty.isPresent()) {
            long nrOfPartys = partyRepository.count();
            party = optionalParty.get();
            model.addAttribute("prevId", id > 1 ? id - 1 : nrOfPartys);
            model.addAttribute("nextId", id < nrOfPartys ? id + 1 : 1);
        }
        else{
            party=null;
        }   model.addAttribute("party", party);
            return "partydetails";
        }


}
