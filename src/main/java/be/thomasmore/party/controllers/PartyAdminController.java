package be.thomasmore.party.controllers;

import be.thomasmore.party.model.Party;
import be.thomasmore.party.model.Venue;
import be.thomasmore.party.repositories.PartyRepository;
import be.thomasmore.party.repositories.VenueRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Controller
@RequestMapping("/admin")
public class PartyAdminController {
    @Autowired
    private PartyRepository partyRepository;
    @Autowired
    private VenueRepository venueRepository;

    private Logger logger = LoggerFactory.getLogger(PartyAdminController.class);

    @ModelAttribute("party")
    public Party findParty(@PathVariable(required = false) Integer id){
        logger.info("findParty " + id);
        if(id == null) return new Party();
        Optional<Party> optionalParty = partyRepository.findById(id);
        if(optionalParty.isPresent()) return optionalParty.get();
        return null;
    }

    @GetMapping({"/partyedit/{id}"})
    public String partyEdit(Model model, @PathVariable (required = false) Integer id) {
        List<Venue> optionalVenues = (List<Venue>) venueRepository.findAll();
        if(!optionalVenues.isEmpty()) model.addAttribute("venues", optionalVenues);
        return "admin/partyedit";
    }

    @PostMapping("/partyedit/{id}")
    public String partyEditPost(@PathVariable int id, Party party){
        //logger.info("findParty " + id + " -- new name=" + party.getName());
        partyRepository.save(party);
        return "redirect:/partydetails/" + id;
    }

    @GetMapping({"/partynew"})
    public String partyNew(Model model) {
        List<Venue> optionalVenues = (List<Venue>) venueRepository.findAll();
        if(!optionalVenues.isEmpty()) model.addAttribute("venues", optionalVenues);
        return "admin/partynew";
    }

}