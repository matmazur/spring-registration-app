package com.matmazur.springvalidationjpa.controllers;

import com.matmazur.springvalidationjpa.model.Person;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.List;

@Controller
public class IndexController {

    @GetMapping("/")
    public String index(ModelMap modelMap) {

        modelMap.put("person", new Person());

        return "index";
    }

    @PostMapping("/")
    public String index(@Valid @ModelAttribute Person person, BindingResult result) {
        if (result.hasErrors()) {
            List<ObjectError> errors = result.getAllErrors();

            errors.forEach(x -> {
                        System.out.printf("%s (%s)\n",
                                x.getObjectName(),
                                x.getDefaultMessage());
                    }
            );
        }
        System.out.println(person);

        return "index";
    }
}