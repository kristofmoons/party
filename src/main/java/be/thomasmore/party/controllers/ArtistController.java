package be.thomasmore.party.controllers;

import be.thomasmore.party.model.Artist;
import be.thomasmore.party.repositories.ArtistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
public class ArtistController {

    @Autowired
    private ArtistRepository artistRepository;



    @GetMapping({"/artistdetails", "/artistdetails/{id}"})
    public String artistDetails(Model model,
                                @PathVariable(required = false) Integer id) {
        if (id == null) return "artistdetails";

        Optional <Artist> optionalArtist = artistRepository.findById(id);
        if (optionalArtist.isPresent()) {
            long nrOfArtists = artistRepository.count();
            model.addAttribute("artist", optionalArtist.get());
            model.addAttribute("prevId", id > 1 ? id - 1 : nrOfArtists);
            model.addAttribute("nextId", id < nrOfArtists ? id + 1 : 1);
            model.addAttribute("parties",optionalArtist.get().getParties());

        }
        return "artistdetails";
    }

    @GetMapping("/artistlist")
    public String artistList(Model model) {
        Iterable <Artist> artists = artistRepository.findAll();
        long nrOfArtists = artistRepository.count();
        model.addAttribute("artists", artists);
        model.addAttribute("showFilters", false);
        model.addAttribute("nrOfArtists", nrOfArtists);
        return "artistlist";
    }
    @GetMapping("/artistlist/filter")
    public String artistListFilter(Model model, @RequestParam (required = false) String keyword){
        Iterable <Artist> jos = artistRepository.findByKeyword(keyword);

        int nrOfArtists =0;
        for (Artist a :jos){
            nrOfArtists++;
        }
        model.addAttribute("nrOfArtists", nrOfArtists);
        model.addAttribute("artists", jos);
        model.addAttribute("showFilters", true);
        return "artistlist";
    }
}
