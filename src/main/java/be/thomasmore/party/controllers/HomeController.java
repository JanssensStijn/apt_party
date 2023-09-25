package be.thomasmore.party.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping({"/", "/home"})
    public String home(Model model) {
        int myCalculatedValue = 5 * 12;
        String appName = "Mediatheek";
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
        return "about";
    }
}
