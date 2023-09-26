package be.thomasmore.party.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Controller
public class HomeController {

    String appName = "Party";

    @GetMapping({"/", "/home"})
    public String home(Model model) {
        int myCalculatedValue = 5 * 12;
        model.addAttribute("myCalculatedValue", myCalculatedValue);
        model.addAttribute("appName",appName);
        return "home";
    }

    @GetMapping("/about")
    public String about(Model model) {
        String myName = "Stijn Janssens";
        String myStreet = "Braambessenweg 2";
        String myCity = "Aarschot";
        model.addAttribute("myName", myName);
        model.addAttribute("myStreet", myStreet);
        model.addAttribute("myCity", myCity);
        model.addAttribute("appName",appName);
        return "about";
    }

    @GetMapping("/pay")
    public String pay(Model model) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDateTime dateNow = LocalDateTime.now();
        LocalDateTime dateMax = LocalDateTime.now().plusDays(30);
        model.addAttribute("appName",appName);
        model.addAttribute("dateNow",dateNow.format(formatter));
        model.addAttribute("dateMax",dateMax.format(formatter));
        return "pay";
    }
}
