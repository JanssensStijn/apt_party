package be.thomasmore.party.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.DayOfWeek;

@Controller
public class HomeController {

    @GetMapping({"/", "/home"})
    public String home(Model model) {

        return "home";
    }

    @GetMapping("/about")
    public String about(Model model) {
        String myName = "Stijn";
        String myCourse = "Graduaat Programmeren";
        String mySchool = "Thomas More - De Nayer";
        model.addAttribute("myName", myName);
        model.addAttribute("myCourse", myCourse);
        model.addAttribute("mySchool", mySchool);
        return "about";
    }

    @GetMapping("/pay")
    public String pay(Model model) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDateTime dateNow = LocalDateTime.now();
        LocalDateTime dateMax = LocalDateTime.now().plusDays(30);
        boolean isWeekend = false;
        if(dateNow.getDayOfWeek() == DayOfWeek.SATURDAY || dateNow.getDayOfWeek() == DayOfWeek.SUNDAY)  isWeekend = true;
        model.addAttribute("dateNow",dateNow.format(formatter));
        model.addAttribute("dateMax",dateMax.format(formatter));
        model.addAttribute("isWeekend",isWeekend);
        return "pay";
    }
}
