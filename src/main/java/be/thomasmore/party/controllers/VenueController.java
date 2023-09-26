package be.thomasmore.party.controllers;

import be.thomasmore.party.model.Venue;
import be.thomasmore.party.repositories.VenueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Optional;

@Controller
public class VenueController {
    @Autowired
    private VenueRepository venueRepository;

    @GetMapping({"/venuedetails"})
    public String venuedetails(Model model) {
        /*Venue myVenue = new Venue();
        myVenue.setVenueName("Boesj");
        myVenue.setLinkMoreInfo("https://sass-lang.com/");
        myVenue.setCapacity(100);
        myVenue.setFoodProvided(false);
        myVenue.setIndoor(true);
        myVenue.setOutdoor(false);
        myVenue.setFreeParkingAvailable(false);
        myVenue.setCity("Mechelen");
        myVenue.setDistanceFromPublicTransportInKm(1);
        model.addAttribute("Venue", myVenue);*/

        Optional<Venue> venueFromDb = venueRepository.findById(1);
        if (venueFromDb.isPresent()) model.addAttribute("venue", venueFromDb.get());
        return "venuedetails";
    }
}