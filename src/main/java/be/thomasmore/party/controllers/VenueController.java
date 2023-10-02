package be.thomasmore.party.controllers;

import be.thomasmore.party.model.Client;
import be.thomasmore.party.model.Venue;
import be.thomasmore.party.repositories.VenueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Optional;

@Controller
public class VenueController {

    String appName = "Party";
    @Autowired
    private VenueRepository venueRepository;

    @GetMapping({"/venuedetails"})
    public String venuedetails(Model model) {
        model.addAttribute("appName", appName);
        Optional<Venue> venueFromDb = venueRepository.findById(1);
        final Venue venue = venueFromDb.get();
        if (venueFromDb.isPresent()) model.addAttribute("venue", venue);
        return "venuedetails";
    }
}