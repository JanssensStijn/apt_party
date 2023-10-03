package be.thomasmore.party.controllers;

import be.thomasmore.party.model.Artist;
import be.thomasmore.party.model.Venue;
import be.thomasmore.party.repositories.ArtistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@Controller
public class ArtistController {

    @Autowired
    private ArtistRepository artistRepository;

    @GetMapping({"/artistlist"})
    public String artistlist(Model model) {
        final Iterable<Artist> allArtists = artistRepository.findAll();
        model.addAttribute("artists", allArtists);
        return "artistlist";
    }
    @GetMapping({"/venuedetails/{id}","/venuedetails", "/venuedetails/"})
    public String venuedetails(Model model, @PathVariable (required = false) Integer id) {
        if(id == null) return "venuedetails";
        Optional<Venue> venueFromDb = venueRepository.findById(id);
        final Venue venue = venueFromDb.get();
        if (venueFromDb.isPresent()) model.addAttribute("venue", venue);
        return "venuedetails";
    }
}