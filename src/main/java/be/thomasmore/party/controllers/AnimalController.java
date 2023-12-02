package be.thomasmore.party.controllers;

import be.thomasmore.party.model.Animal;
import be.thomasmore.party.repositories.AnimalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@Controller
public class AnimalController {

    @Autowired
    private AnimalRepository animalRepository;

    @GetMapping({"/animaldetails/{id}","/animaldetails", "/animaldetails/"})
    public String animaldetails(Model model, @PathVariable (required = false) Integer id) {
        if (id == null) return "artistdetails";
        Optional<Animal> animalFromDb = animalRepository.findById(id);

        if (animalFromDb.isPresent()){
            Optional<Animal> nextArtistFromDb = animalRepository.findFirstByIdGreaterThanOrderByIdAsc(id);
            if (nextArtistFromDb.isEmpty()) //if no venue id is higher, get the venue with the lowest id
                nextArtistFromDb = animalRepository.findFirstByOrderByIdAsc();
            Optional<Animal> prevArtistFromDb = animalRepository.findFirstByIdLessThanOrderByIdDesc(id);
            if (prevArtistFromDb.isEmpty()) //if no venue id is lower, get the venue with the highest id
                prevArtistFromDb = animalRepository.findFirstByOrderByIdDesc();

            model.addAttribute("nextId", nextArtistFromDb.get().getId());
            model.addAttribute("prevId", prevArtistFromDb.get().getId());
            model.addAttribute("animal", animalFromDb.get());
            model.addAttribute("parties", animalFromDb.get().getParties());
        }

        return "animaldetails";
    }
}