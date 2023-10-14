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

    @Autowired
    private ClientRepository clientRepository;

    @GetMapping({"/clienthome"})
    public String clienthome(Model model) {
        Optional<Client> clientFromDb = clientRepository.findById(1);
        final Client client = clientFromDb.get();
        String message = "deze klant kan niet gevonden worden";
        if (clientFromDb.isPresent()){
            message = "klant \"" + client.getName() + "\" gevonden";
        }
        model.addAttribute("message", message);
        return "clienthome";
    }

    @GetMapping({"/clientdetails"})
    public String clientdetails(Model model) {
        Optional<Client> clientFromDb = clientRepository.findById(1);
        final Client client = clientFromDb.get();
        String message = "deze klant kan niet gevonden worden";
        if (clientFromDb.isPresent()){
            double discount = 0;
            if(client.getTotalAmount() >= 100) discount = client.getTotalAmount() * 0.005;
            message = client.getName() + ", je discount is " + discount + "EUR";
        }
        model.addAttribute("message", message);
        return "clientdetails";
    }

    @GetMapping({"/clientgreeting"})
    public String clientgreeting(Model model) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH");
        LocalDateTime timeNow = LocalDateTime.now();
        int currentHour = Integer.parseInt(timeNow.format(formatter));

        String greetingPart1;
        if (currentHour >= 6 && currentHour < 12) greetingPart1 = "Goedemorgen ";
        else if (currentHour >= 12 && currentHour < 17) greetingPart1 = "Goedemiddag ";
        else if (currentHour >= 17 && currentHour < 22) greetingPart1 = "Goedenavond ";
        else  greetingPart1 = "Goedennacht ";

        model.addAttribute("greetingPart1", greetingPart1);

        Optional<Client> clientFromDb = clientRepository.findById(1);
        final Client client = clientFromDb.get();
        String message = "deze klant kan niet gevonden worden";
        if (clientFromDb.isPresent()){
            String greetingPart2 = "", greetingPart3 = "";
            if(client.getNrOfOrders() == 0) {
                greetingPart2 = "";
                greetingPart3 = ", en welkom!";
            }
            else if(client.getNrOfOrders() >= 10 && clientFromDb.get().getNrOfOrders() < 50) {
                greetingPart2 = "beste ";
                greetingPart3 = "";
            }
            else if(client.getNrOfOrders() >= 50 && clientFromDb.get().getNrOfOrders() < 80) {
                greetingPart2 = "allerliefste ";
                greetingPart3 = "";
            }
            else if(client.getNrOfOrders() >= 80) {
                greetingPart2 = "allerliefste ";
                greetingPart3 = ", jij bent een topper!";
            }

            message = greetingPart1 + greetingPart2 + client.getName() + greetingPart3;
        }
        model.addAttribute("message", message);
        return "clientgreeting";
    }
}
