package be.thomasmore.party.controllers;

        import be.thomasmore.party.model.Animal;
        import be.thomasmore.party.model.Party;
        import be.thomasmore.party.model.Party;
        import be.thomasmore.party.repositories.AnimalRepository;
        import be.thomasmore.party.repositories.PartyRepository;
        import jakarta.validation.Valid;
        import org.slf4j.Logger;
        import org.slf4j.LoggerFactory;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.stereotype.Controller;
        import org.springframework.ui.Model;
        import org.springframework.validation.BindingResult;
        import org.springframework.web.bind.annotation.*;

        import java.security.Principal;
        import java.util.List;
        import java.util.Optional;

@Controller
public class PartyController {
    private Logger logger = LoggerFactory.getLogger(PartyController.class);

    @Autowired
    private PartyRepository partyRepository;
    @Autowired
    private AnimalRepository animalRepository;

    @ModelAttribute("party")
    public Party findParty(@PathVariable(required = false) Integer id){
        logger.info("findParty " + id);
        if(id == null) return new Party();
        Optional<Party> optionalParty = partyRepository.findById(id);
        if(optionalParty.isPresent()) return optionalParty.get();
        return null;
    }
    @GetMapping({"/partydetails/{id}", "/partydetails", "/partydetails/"})
    public String partydetails(Model model,Party party, @PathVariable(required = false) Integer id, Principal principal) {

        if (id == null) return "partydetails";
        Optional<Party> partyFromDb = partyRepository.findById(id);

        if (partyFromDb.isPresent()){
            Optional<Party> nextpartyFromDb = partyRepository.findFirstByIdGreaterThanOrderByIdAsc(id);
            if (nextpartyFromDb.isEmpty()) //if no Party id is higher, get the Party with the lowest id
                nextpartyFromDb = partyRepository.findFirstByOrderByIdAsc();
            Optional<Party> prevpartyFromDb = partyRepository.findFirstByIdLessThanOrderByIdDesc(id);
            if (prevpartyFromDb.isEmpty()) //if no Party id is lower, get the Party with the highest id
                prevpartyFromDb = partyRepository.findFirstByOrderByIdDesc();

            Animal animalGoing = animalRepository.findByUsername(principal.getName());
            model.addAttribute("going", party.getAnimals().contains(animalGoing));
            model.addAttribute("nextId", nextpartyFromDb.get().getId());
            model.addAttribute("prevId", prevpartyFromDb.get().getId());
            model.addAttribute("artists", partyFromDb.get().getArtists());
            model.addAttribute("animals", partyFromDb.get().getAnimals());
        }
        return "partydetails";
    }
    @PostMapping("/partydetails/{id}")
    public String partyGoingPost(Model model,
                                 @PathVariable int id,
                                 Party party, Principal principal){

        Animal animalGoing = animalRepository.findByUsername(principal.getName());
        model.addAttribute("going", party.getAnimals().contains(animalGoing));

        if(party.getAnimals().contains(animalGoing)) animalGoing.getParties().remove(party);
        else animalGoing.getParties().add(party);
                animalRepository.save(animalGoing);
        return "redirect:/partydetails/" + id;
    }

    @GetMapping({"/partylist", "/partylist/",})
    public String partylist(Model model) {
        final Iterable<Party> allPartys = partyRepository.findAll();
        model.addAttribute("partys", allPartys);
        return "partylist";
    }
}