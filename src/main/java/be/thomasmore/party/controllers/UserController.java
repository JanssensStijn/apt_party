package be.thomasmore.party.controllers;

import be.thomasmore.party.model.Artist;
import be.thomasmore.party.model.Venue;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.List;
@Controller
@RequestMapping("/user")
public class UserController {
    @GetMapping({"/login"})
    public String login(Model model, Principal principal) {
        if(principal != null) return "redirect: /partylist";
        return "user/login";
    }
}
