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

    @GetMapping("/animaldetails/{animalID}")
    public String animalDetails(Model model, @PathVariable(required = false) Integer animalID){
        Animal animal;
        Optional<Animal> optional = animalRepository.findById(animalID);
        if (optional.isPresent()) {
            animal = optional.get();
        } else {
            animal = null;
        }
        model.addAttribute("animal", animal);
        return "animaldetails";
    }
}
