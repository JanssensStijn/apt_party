package be.thomasmore.party.controllers;

import be.thomasmore.party.model.Artist;
import be.thomasmore.party.model.Party;
import be.thomasmore.party.model.Venue;
import be.thomasmore.party.repositories.ArtistRepository;
import be.thomasmore.party.repositories.PartyRepository;
import be.thomasmore.party.repositories.VenueRepository;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.naming.Binding;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/admin")
public class PartyAdminController {
    @Autowired
    private PartyRepository partyRepository;
    @Autowired
    private VenueRepository venueRepository;
    @Autowired
    private ArtistRepository artistRepository;

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
        List<Artist> optionalArtists = (List<Artist>) artistRepository.findAll();
        if(!optionalArtists.isEmpty()) model.addAttribute("artists", optionalArtists);
        return "admin/partyedit";
    }

    @PostMapping("/partyedit/{id}")
    public String partyEditPost(Model model,
                                @PathVariable int id,
                                @Valid Party party,
                                BindingResult bindingResult ){
        //logger.info("findParty " + id + " -- new name=" + party.getName());
        if(bindingResult.hasErrors()){
            model.addAttribute("venues", venueRepository.findAll());
            model.addAttribute("artists", artistRepository.findAll());
            return "admin/partyedit";
        }
        partyRepository.save(party);
        return "redirect:/partydetails/" + id;
    }

    @GetMapping({"/partynew"})
    public String partyNew(Model model) {
        List<Venue> optionalVenues = (List<Venue>) venueRepository.findAll();
        if(!optionalVenues.isEmpty()) model.addAttribute("venues", optionalVenues);
        List<Artist> optionalArtists = (List<Artist>) artistRepository.findAll();
        if(!optionalArtists.isEmpty()) model.addAttribute("artists", optionalArtists);
        return "admin/partynew";
    }

    @PostMapping("/partynew")
    public String partyNewPost(Model model,
                               @Valid Party party,
                               BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            model.addAttribute("venues", venueRepository.findAll());
            model.addAttribute("artists", artistRepository.findAll());
            return "admin/partynew";
        }
        Party newParty = partyRepository.save(party);
        return "redirect:/partydetails/" + newParty.getId();
    }

}