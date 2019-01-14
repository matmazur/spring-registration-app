package com.matmazur.springvalidationjpa.controllers;

import com.matmazur.springvalidationjpa.model.Person;
import com.matmazur.springvalidationjpa.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Comparator;
import java.util.List;

@Controller
public class IndexController {

    private final
    PersonRepository personRepository;

    @Autowired
    public IndexController(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @GetMapping("/")
    public String index(ModelMap modelMap) {

        List<Person> people = personRepository.findAll();
        if (!people.isEmpty()) {
            people.sort(Comparator.comparing(Person::getId));
            modelMap.put("people", people);
        }
        return "index";
    }
}