package be.thomasmore.party.controllers;

        import be.thomasmore.party.model.Party;
        import be.thomasmore.party.model.Party;
        import be.thomasmore.party.repositories.PartyRepository;
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
public class PartyController {
    private Logger logger = LoggerFactory.getLogger(PartyController.class);

    @Autowired
    private PartyRepository partyRepository;

    @GetMapping({"/partydetails/{id}", "/partydetails", "/partydetails/"})
    public String partydetails(Model model, @PathVariable(required = false) Integer id) {

        if (id == null) return "partydetails";
        Optional<Party> partyFromDb = partyRepository.findById(id);

        if (partyFromDb.isPresent()){
            Optional<Party> nextpartyFromDb = partyRepository.findFirstByIdGreaterThanOrderByIdAsc(id);
            if (nextpartyFromDb.isEmpty()) //if no Party id is higher, get the Party with the lowest id
                nextpartyFromDb = partyRepository.findFirstByOrderByIdAsc();
            Optional<Party> prevpartyFromDb = partyRepository.findFirstByIdLessThanOrderByIdDesc(id);
            if (prevpartyFromDb.isEmpty()) //if no Party id is lower, get the Party with the highest id
                prevpartyFromDb = partyRepository.findFirstByOrderByIdDesc();

            model.addAttribute("nextId", nextpartyFromDb.get().getId());
            model.addAttribute("prevId", prevpartyFromDb.get().getId());
            model.addAttribute("party", partyFromDb.get());
            model.addAttribute("artists", partyFromDb.get().getArtists());
        }

        return "partydetails";
    }

    @GetMapping({"/partylist", "/partylist/",})
    public String partylist(Model model) {
        final Iterable<Party> allPartys = partyRepository.findAll();
        model.addAttribute("partys", allPartys);
        return "partylist";
    }
}