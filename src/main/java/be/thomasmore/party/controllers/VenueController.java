package be.thomasmore.party.controllers;

import be.thomasmore.party.model.Venue;
import be.thomasmore.party.repositories.VenueRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Controller
public class VenueController {
    private Logger logger = LoggerFactory.getLogger(VenueController.class);

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
            model.addAttribute("venue", venueFromDb.get());
        }

        return "venuedetails";
    }

    @GetMapping({"/venuelist", "/venuelist/",})
    public String venuelist(Model model) {
        final Iterable<Venue> allVenues = venueRepository.findAll();
        final long numberOfVenues = venueRepository.count();
        model.addAttribute("numberOfVenues", numberOfVenues);
        model.addAttribute("venues", allVenues);
        model.addAttribute("showFilter", false);
        return "venuelist";
    }

    @GetMapping({"/venuelist/filter"})
    public String venueListWithFilter(Model model,
                                      @RequestParam(required = false) Integer minCapacity,
                                      @RequestParam(required = false) Integer maxCapacity,
                                      @RequestParam(required = false) Integer maxDistance,
                                      @RequestParam(required = false) Boolean isFoodProvided) {

        logger.info(String.format("venueListWithFilter -- min = %d -- max = %d", minCapacity, maxCapacity) + "food: " + isFoodProvided);
        final List<Venue> allVenues = venueRepository.filter(minCapacity,maxCapacity, maxDistance, isFoodProvided);

        model.addAttribute("minCapacityFiltered" , minCapacity);
        model.addAttribute("maxCapacityFiltered" , maxCapacity);
        model.addAttribute("maxDistanceFiltered" , maxDistance);
        model.addAttribute("isFoodProvidedFiltered", isFoodProvided);
        model.addAttribute("numberOfVenues" , allVenues.size());
        model.addAttribute("venues", allVenues);
        model.addAttribute("showFilter", true);
        return "venuelist";
    }

    @GetMapping({"/error"})
    public String error(Model model) {
        return "error";
    }
}