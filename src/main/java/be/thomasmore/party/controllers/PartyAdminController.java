package be.thomasmore.party.controllers;

import be.thomasmore.party.model.Party;
import be.thomasmore.party.repositories.PartyRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
@RequestMapping("/admin")
public class PartyAdminController {
    @Autowired
    private PartyRepository partyRepository;

    private Logger logger = LoggerFactory.getLogger(PartyAdminController.class);

    @GetMapping({"/partyedit/{id}","/partyedit", "/partyedit/"})
    public String animaldetails(Model model, @PathVariable (required = false) Integer id) {
        if (id == null) return "partydetails";
        Optional<Party> partyFromDb = partyRepository.findById(id);

        if (partyFromDb.isPresent()){
            Optional<Party> nextpartyFromDb = partyRepository.findFirstByIdGreaterThanOrderByIdAsc(id);
            if (nextpartyFromDb.isEmpty()) //if no Party id is higher, get the Party with the lowest id
                nextpartyFromDb = partyRepository.findFirstByOrderByIdAsc();
            Optional<Party> prevpartyFromDb = partyRepository.findFirstByIdLessThanOrderByIdDesc(id);
            if (prevpartyFromDb.isEmpty()) //if no Party id is lower, get the Party with the highest id
                prevpartyFromDb = partyRepository.findFirstByOrderByIdDesc();

            model.addAttribute("party", partyFromDb.get());
        }

        return "admin/partyedit";
    }
    @ModelAttribute("party")
}