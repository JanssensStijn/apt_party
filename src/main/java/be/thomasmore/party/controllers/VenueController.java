package be.thomasmore.party.controllers;

import be.thomasmore.party.model.Venue;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class VenueController {

    @GetMapping({"/venuedetails"})
    public String venuedetails(Model model) {
        Venue myVenue = new Venue();
        myVenue.setVenueName("Boesj");
        myVenue.setLinkMoreInfo("https://sass-lang.com/");
        myVenue.setCapacity(100);
        myVenue.setFoodProvided(false);
        myVenue.setIndoor(true);
        myVenue.setOutdoor(false);
        myVenue.setFreeParkingAvailable(false);
        myVenue.setCity("Mechelen");
        myVenue.setDistanceFromPublicTransportInKm(1);
        model.addAttribute("myVenue", myVenue);
        return "venuedetails";
    }
}