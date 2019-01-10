package com.matmazur.springvalidationjpa;

import com.matmazur.springvalidationjpa.model.Person;
import com.matmazur.springvalidationjpa.services.PersonService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class SpringValidationJpaApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext ctx = SpringApplication.run(SpringValidationJpaApplication.class, args);
        PersonService personService = ctx.getBean(PersonService.class);

        Person person = new Person("Jack", "Black", "nice@emailbutstillwrong", 52);
        Person person2 = new Person("Jack", "", null, -52);

        personService.add(person);
        personService.add(person2);

        ctx.close();
    }
}


