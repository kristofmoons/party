package be.thomasmore.party.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class HomeController {
    private final int mySpecialNumber = 729;

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("mySpecialNumber", mySpecialNumber);
        return "home";
    }

    @GetMapping("/about")
    public String about(Model model) {
        model.addAttribute("mySpecialNumber", mySpecialNumber);
        return "about";
    }

    @GetMapping("/pay")
    public String pay(Model model) {
        model.addAttribute("mySpecialNumber", mySpecialNumber);
        return "pay";
    }
    @GetMapping("/venuedetails/{venueName}")
    public String venueDetails(Model model,
                               @PathVariable String venueName) {
        model.addAttribute("venueName", venueName);
        return "venuedetails";
    }
}
