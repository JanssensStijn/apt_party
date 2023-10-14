package be.thomasmore.party.controllers;

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

        if (venueFromDb.isPresent()){
            Optional<Venue> nextVenueFromDb = venueRepository.findFirstByIdGreaterThanOrderByIdAsc(id);
            if (nextVenueFromDb.isEmpty()) //if no venue id is higher, get the venue with the lowest id
                nextVenueFromDb = venueRepository.findFirstByOrderByIdAsc();
            Optional<Venue> prevVenueFromDb = venueRepository.findFirstByIdLessThanOrderByIdDesc(id);
            if (prevVenueFromDb.isEmpty()) //if no venue id is lower, get the venue with the highest id
                prevVenueFromDb = venueRepository.findFirstByOrderByIdDesc();

            model.addAttribute("nextId", nextVenueFromDb.get().getId());
            model.addAttribute("prevId", prevVenueFromDb.get().getId());
            model.addAttribute("venue", nextVenueFromDb.get());
        }

        return "venuedetails";
    }

    @GetMapping({"/venuelist", "/venuelist/","/venuelist/{filter}","/venuelist/{filter}/","/venuelist/outdoor/all", "/venuelist/indoor/all", "/venuelist/capacity/all" })
    public String venuelist(Model model, @PathVariable(required = false) String filter) {
        final Iterable<Venue> allVenues = venueRepository.findAll();
        model.addAttribute("venues", allVenues);
        model.addAttribute("outdoorFilter", "all");
        model.addAttribute("indoorFilter", "all");
        model.addAttribute("capacityFilter", "all");
        return "venuelist";
    }

    @GetMapping({"/venuelist/outdoor/{filter}"})
    public String venuelistOutdoor(Model model, @PathVariable(required = false) String filter) {
        final Iterable<Venue> allVenues = venueRepository.findByOutdoor(filter.equals("yes"));
        model.addAttribute("venues", allVenues);
        if(filter.equals("yes")||filter.equals("no")) model.addAttribute("outdoorFilter", filter );
        else model.addAttribute("outdoorFilter", "no" );
        model.addAttribute("indoorFilter", "all" );
        model.addAttribute("capacityFilter", "all");
        return "venuelist";
    }

    @GetMapping({"/venuelist/indoor/{filter}"})
    public String venuelistIndoor(Model model, @PathVariable(required = false) String filter) {
        final Iterable<Venue> allVenues = venueRepository.findByIndoor(filter.equals("yes"));
        model.addAttribute("venues", allVenues);
        if(filter.equals("yes")||filter.equals("no")) model.addAttribute("indoorFilter", filter );
        else model.addAttribute("indoorFilter", "no" );
        model.addAttribute("outdoorFilter", "all" );
        model.addAttribute("capacityFilter", "all");
        return "venuelist";
    }

    @GetMapping({"/venuelist/capacity/{filter}"})
    public String venuelistCapacity(Model model, @PathVariable(required = false) String filter) {
        switch(filter){
            case "S":  final Iterable<Venue> allSmallVenues = venueRepository.findByCapacityLessThan(200);
                        model.addAttribute("venues", allSmallVenues);
            break;
            case "M":  final Iterable<Venue> allMediumVenues = venueRepository.findByCapacityBetween(200, 600);
                        model.addAttribute("venues", allMediumVenues);
                break;
            case "L":  final Iterable<Venue> allLargeVenues = venueRepository.findByCapacityGreaterThan(600);
                        model.addAttribute("venues", allLargeVenues);
                break;
            default:  final Iterable<Venue> allVenues = venueRepository.findAll();
                model.addAttribute("venues", allVenues);
            break;
        }

        if(filter.equals("S")||filter.equals("M")||filter.equals("L")) model.addAttribute("capacityFilter", filter );
        else model.addAttribute("capacityFilter", "all");
        model.addAttribute("indoorFilter", "all");
        model.addAttribute("outdoorFilter", "all" );
        return "venuelist";
    }

    @GetMapping({"/error"})
    public String error(Model model) {
        return "error";
    }
}