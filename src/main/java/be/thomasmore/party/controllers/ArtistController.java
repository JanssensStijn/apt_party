package be.thomasmore.party.controllers;

import be.thomasmore.party.model.Artist;
import be.thomasmore.party.model.Venue;
import be.thomasmore.party.repositories.ArtistRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.w3c.dom.Text;

import java.util.Optional;

@Controller
public class ArtistController {
    private Logger logger = LoggerFactory.getLogger(VenueController.class);

    @Autowired
    private ArtistRepository artistRepository;

    @GetMapping({"/artistlist"})
    public String artistlist(Model model) {
        final Iterable<Artist> allArtists = artistRepository.findAll();
        final long numberOfArtists = artistRepository.count();
        model.addAttribute("numberOfArtists" , numberOfArtists);
        model.addAttribute("artists", allArtists);
        model.addAttribute("showFilter", false);
        return "artistlist";
    }

    @GetMapping({"/artistlist/filter"})
    public String artistListWithFilter(Model model,
                                      @RequestParam(required = false) String textFilter ){

        logger.info("artistlistWithFilter -- name = "+ textFilter);
        final Iterable<Artist> allArtists;
        final long numberOfArtists;

        if(textFilter != null){
            allArtists = artistRepository.findByArtistNameContainsIgnoreCase(textFilter);
            numberOfArtists = artistRepository.findAllByArtistNameContainsIgnoreCase(textFilter).size();
        }
        else {
            allArtists = artistRepository.findAll();
            numberOfArtists = artistRepository.count();
        }

        model.addAttribute("textFilter" , textFilter);
        model.addAttribute("numberOfArtists" , numberOfArtists);
        model.addAttribute("artists", allArtists);
        model.addAttribute("showFilter", true);
        return "artistlist";
    }

    @GetMapping({"/artistdetails/{id}","/artistdetails", "/artistdetails/"})
    public String artistdetails(Model model, @PathVariable (required = false) Integer id) {
        if (id == null) return "artistdetails";
        Optional<Artist> artistFromDb = artistRepository.findById(id);

        if (artistFromDb.isPresent()){
            Optional<Artist> nextArtistFromDb = artistRepository.findFirstByIdGreaterThanOrderByIdAsc(id);
            if (nextArtistFromDb.isEmpty()) //if no venue id is higher, get the venue with the lowest id
                nextArtistFromDb = artistRepository.findFirstByOrderByIdAsc();
            Optional<Artist> prevArtistFromDb = artistRepository.findFirstByIdLessThanOrderByIdDesc(id);
            if (prevArtistFromDb.isEmpty()) //if no venue id is lower, get the venue with the highest id
                prevArtistFromDb = artistRepository.findFirstByOrderByIdDesc();

            model.addAttribute("nextId", nextArtistFromDb.get().getId());
            model.addAttribute("prevId", prevArtistFromDb.get().getId());
            model.addAttribute("artist", artistFromDb.get());
        }

        return "artistdetails";
    }
}