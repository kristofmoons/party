package be.thomasmore.party.controllers;

import be.thomasmore.party.model.Venue;
import be.thomasmore.party.repositories.PartyRepository;
import be.thomasmore.party.repositories.VenueRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
public class VenueController {
    @Autowired
    private VenueRepository venueRepository;
    @Autowired
    private PartyRepository partyRepository;

    private Logger logger = LoggerFactory.getLogger(VenueController.class);


    @GetMapping({"/venuedetails", "/venuedetails/{id}"})
    public String venueDetails(Model model,
                               @PathVariable(required = false) Integer id) {
        if (id == null) return "venuedetails";

        Optional<Venue> optionalVenue = venueRepository.findById(id);
        if (optionalVenue.isPresent()) {
            long nrOfVenues = venueRepository.count();
            model.addAttribute("venue", optionalVenue.get());
            model.addAttribute("prevId", id > 1 ? id - 1 : nrOfVenues);
            model.addAttribute("nextId", id < nrOfVenues ? id + 1 : 1);
            model.addAttribute("parties",partyRepository.findPartiesByVenue(optionalVenue.get()));
        }
        return "venuedetails";
    }

    @GetMapping("/venuelist")
    public String venueList(Model model) {
        Iterable<Venue> venues = venueRepository.findAll();
        long nrOfVenues = venueRepository.count();
        model.addAttribute("nrOfVenues", nrOfVenues);
        model.addAttribute("venues", venues);
        model.addAttribute("showFilters", false);
        return "venuelist";
    }

    @GetMapping("/venuelist/filter")
    public String venueListFilter(Model model,
                                  @RequestParam(required = false) Integer maxDistance,
                                  @RequestParam(required = false) Integer minCapacity,
                                  @RequestParam(required = false) Integer maxCapacity,
                                  @RequestParam(required = false) Boolean food,
                                  @RequestParam(required = false) Boolean indoor,
                                  @RequestParam(required = false) Boolean outdoor){

        logger.info("venueListWithFilter -- min=%d, max=%d", minCapacity, maxCapacity);
        Iterable<Venue> venues;
        venues = venueRepository.findByCriteria(minCapacity, maxCapacity, maxDistance,food,indoor,outdoor);

        int nrOfVenues = 0;
        for (Venue v: venues){
            nrOfVenues++;
        }
        model.addAttribute("nrOfVenues", nrOfVenues);
        model.addAttribute("venues", venues);
        model.addAttribute("showFilters", true);
        model.addAttribute("minCapacity", minCapacity);
        model.addAttribute("maxCapacity", maxCapacity);
        model.addAttribute("maxDistance", maxDistance);
        model.addAttribute("food",food);
        model.addAttribute("indoor", indoor);
        model.addAttribute("outdoor",outdoor);
        return "venuelist";
    }
}
