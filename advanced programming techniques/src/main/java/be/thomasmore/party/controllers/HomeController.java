package be.thomasmore.party.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;


@Controller
public class HomeController {
    private final String[] venueNames = {"De Loods", "De Club", "De Hangar", "Zapoi", "Kuub", "Cupa Libre"};

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

    @GetMapping({"/venuedetails/{venueIndex}", "/venuedetails"})
    public String venueDetails(Model model, @PathVariable(required = false) Double venueIndex){
        if (venueIndex != null && venueNames.length > Math.floor(venueIndex) && Math.floor(venueIndex) >= 0) {
            String venue = venueNames[(int)Math.floor(venueIndex)];
            model.addAttribute("venueName", venue);
        } else {
            model.addAttribute("venueName", "Choose a number between 0 and " + (venueNames.length-1));
        }
        model.addAttribute("venueIndexMax", venueNames.length-1);
        return "venuedetails";
    }

    @GetMapping("/venuelist")
    public String venueList(Model model){
        model.addAttribute("venueName", venueNames);
        return "venuelist";
    }

    @GetMapping("/error")
    public String error(Model model){
        return "error";
    }

}