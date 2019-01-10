package com.matmazur.springvalidationjpa.services;

import com.matmazur.springvalidationjpa.model.Person;
import com.matmazur.springvalidationjpa.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.Set;

@Service
public class PersonService {

    private PersonRepository repository;

    @Autowired
    public PersonService(PersonRepository personRepository) {
        repository = personRepository;
    }

    public void add(Person person) {
        try {
            repository.save(person);
        } catch (ConstraintViolationException e) {
            Set<ConstraintViolation<?>> errors = e.getConstraintViolations();
            for (ConstraintViolation err : errors) {
                System.err.printf("%s - %s - (%s)\n",
                        err.getPropertyPath(),
                        err.getInvalidValue(),
                        err.getMessage());
            }
        }
    }
}
