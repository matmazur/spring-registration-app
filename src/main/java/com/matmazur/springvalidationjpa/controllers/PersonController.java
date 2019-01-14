package com.matmazur.springvalidationjpa.controllers;

import com.matmazur.springvalidationjpa.model.Person;
import com.matmazur.springvalidationjpa.repositories.PersonRepository;
import com.matmazur.springvalidationjpa.services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
public class PersonController {

    private final
    PersonRepository personRepository;

    @Autowired
    public PersonController(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @GetMapping("/add-person")
    public String index(ModelMap modelMap) {

        modelMap.put("person", new Person());
        return "add-person";
    }

    @PostMapping("/add-person")
    public String index(@Valid @ModelAttribute Person person, BindingResult result) {
        if (result.hasErrors()) {
            List<ObjectError> errors = result.getAllErrors();
            errors.forEach(x -> System.out.println(x.getDefaultMessage()));
        }

        if (result.hasErrors()) {
            return "add-person";
        }

        personRepository.save(person);

        return "redirect:/";
    }

    @PostMapping("/clear-list")
    public String clearList() {
        personRepository.deleteAll();
        return "redirect:/";
    }

    @PostMapping("/clear-by-id")
    public String clearId(@RequestParam(name = "id") Long id) {

        if (id != null && personRepository.findById(id).isPresent()) {
            personRepository.deleteById(id);
        }
        return "redirect:/";
    }

    @GetMapping("/delete/{id}")
    public String deleteById(@PathVariable(name = "id") Long id) {

        if (id != null) {
            personRepository.deleteById(id);
        }
        return "redirect:/";
    }
}
