package be.thomasmore.party.controllers;

import model.Venue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;


@Controller
public class HomeController {
    private final Venue[] venues = {new Venue("De Loods"),
            new Venue("De Club"),
            new Venue("De Hangar"),
            new Venue("Zapoi"),
            new Venue("Kuub"),
            new Venue("Cuba Libre"),
            new Venue("Boesj", "https://www.facebook.com/boesjkammeree/", 100, false, true, false, false, "Mechelen", 1)};
    @Autowired
    private VenueRepository venueRepository;

    @GetMapping({"/", "/home"})
    public String home(Model model) {
        return "home";
    }

    @GetMapping("/about")
    public String about(Model model){
        return "about";
    }

    @GetMapping("/pay")
    public String pay(Model model){
        boolean weekend;
        LocalDateTime currentDate = date();
        String dateText = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM).format(currentDate);
        model.addAttribute("dateNow", dateText);
        model.addAttribute("dateLastPay", dateLastPay(currentDate));
        weekend = currentDate.getDayOfWeek().name().equals("SUNDAY") || currentDate.getDayOfWeek().name().equals("SATURDAY");
        model.addAttribute("isWeekend", weekend);
        return "pay";
    }

    private String dateLastPay(LocalDateTime currentDate) {
        LocalDateTime lastPayDate = currentDate.plusDays(30);
        return DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT).format(lastPayDate);
    }

    public LocalDateTime date(){
        LocalDateTime date = LocalDateTime.now();
        return date;
    }

    @GetMapping({"/venuedetails", "/venuedetails/{index}"})
    public String venueDetails(Model model,
                               @PathVariable(required = false) Integer index) {
        if (index != null && index >= 0 && index < venues.length) {
            model.addAttribute("venue", venues[index]);
            model.addAttribute("prevIndex", index > 0 ? index - 1 : venues.length - 1);
            model.addAttribute("nextIndex", index < venues.length - 1 ? index + 1 : 0);
        }
        return "venuedetails";
    }
    @GetMapping({"/venuedetailsbyid","/venuedetailsbyid/{id}"})
    public String venueDetailsById(Model model,
                                   @PathVariable(required = false)Integer id) {
        model.addAttribute("venue", venueRepository.findById(id).get());
        if (id != null && id >= 1 && id < venues.length) {
            model.addAttribute("venue", venues[id]);
            model.addAttribute("prevId", id > 1 ? id - 1 : venues.length - 1);
            model.addAttribute("nextId", id < venues.length - 1 ? id + 1 : 0);

        }
        return "venuedetails";
    }

    @GetMapping("/venuelist")
    public String venueList(Model model) {
        Iterable<Venue>venues= venueRepository.findAll();
        model.addAttribute("venues", venues);
        return "venuelist";
    }

    @GetMapping("/error")
    public String error(Model model){
        return "error";
    }

}