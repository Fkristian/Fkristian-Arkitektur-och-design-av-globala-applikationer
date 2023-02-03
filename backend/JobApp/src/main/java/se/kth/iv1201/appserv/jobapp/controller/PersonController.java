package se.kth.iv1201.appserv.jobapp.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import se.kth.iv1201.appserv.jobapp.domain.Person;
import se.kth.iv1201.appserv.jobapp.domain.external.request.PersonDTO;
import se.kth.iv1201.appserv.jobapp.service.PersonService;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/any")
public class PersonController {
    private final PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping
    public List<Person> getAllUsers(){
        return personService.getAllUsers();
    }

    @PostMapping("register")
    public void registerUser(@RequestBody PersonDTO personDTO){
        personService.registerUser(personDTO);
    }
}
