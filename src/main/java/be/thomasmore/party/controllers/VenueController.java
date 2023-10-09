package be.thomasmore.party.controllers;

import be.thomasmore.party.model.Client;
import be.thomasmore.party.model.Venue;
import be.thomasmore.party.repositories.VenueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@Controller
public class VenueController {

    @Autowired
    private VenueRepository venueRepository;


    @GetMapping({"/venuedetails/{id}", "/venuedetails", "/venuedetails/"})
    public String venuedetails(Model model, @PathVariable(required = false) Integer id) {
        if (id == null) return "venuedetails";
        Optional<Venue> venueFromDb = venueRepository.findById(id);
        final Venue venue = venueFromDb.get();
        if (venueFromDb.isPresent()) model.addAttribute("venue", venue);
        return "venuedetails";
    }

    @GetMapping({"/venuelist", "/venuelist/","/venuelist/{filter}","/venuelist/{filter}/","/venuelist/outdoor/all" })
    public String venuelist(Model model, @PathVariable(required = false) String filter) {
        final Iterable<Venue> allVenues = venueRepository.findAll();
        model.addAttribute("venues", allVenues);
        model.addAttribute("filter", "all");
        return "venuelist";
    }

    @GetMapping({"/venuelist/outdoor/{filter}"})
    public String venuelistOutdoor(Model model, @PathVariable(required = false) String filter) {
        final Iterable<Venue> allVenues = venueRepository.findByOutdoor(filter.equals("yes"));
        model.addAttribute("venues", allVenues);
        if(filter.equals("yes")||filter.equals("no")) model.addAttribute("filter", filter );
        else model.addAttribute("filter", "no" );
        return "venuelist";
    }

    @GetMapping({"/error"})
    public String error(Model model) {
        return "error";
    }
}