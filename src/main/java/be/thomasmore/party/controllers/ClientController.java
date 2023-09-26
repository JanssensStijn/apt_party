package be.thomasmore.party.controllers;

import be.thomasmore.party.model.Client;
import be.thomasmore.party.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

@Controller
public class ClientController {
    String appName = "Party";

    @Autowired
    private ClientRepository clientRepository;

    @GetMapping({"/clientgreeting"})
    public String clientdetails(Model model) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH");
        LocalDateTime timeNow = LocalDateTime.now();
        int currentHour = Integer.parseInt(timeNow.format(formatter));
        String greeting;
        if (currentHour >= 6 && currentHour < 12) greeting = "Goedemorgen";
        else if (currentHour >= 12 && currentHour < 17) greeting = "Goedemiddag";
        else if (currentHour >= 17 && currentHour < 22) greeting = "Goedenavond";
        else  greeting = "Goedennacht";

        model.addAttribute("appName", appName);
        model.addAttribute("greeting", greeting);
        Optional<Client> clientFromDb = clientRepository.findById(1);
        if (clientFromDb.isPresent()) model.addAttribute("client", clientFromDb.get());
        return "clientgreeting";
    }
}
