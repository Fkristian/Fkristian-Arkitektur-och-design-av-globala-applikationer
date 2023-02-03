package se.kth.iv1201.appserv.jobapp.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import se.kth.iv1201.appserv.jobapp.domain.Person;
import se.kth.iv1201.appserv.jobapp.domain.Role;
import se.kth.iv1201.appserv.jobapp.domain.external.request.PersonDTO;
import se.kth.iv1201.appserv.jobapp.repository.PersonRepository;

import java.util.List;

@Service
public class PersonService {

    private final PersonRepository personRepository;

    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public List<Person> getAllUsers(){
        return personRepository.findAll();
    }

    @Transactional
    public void registerUser(PersonDTO personDTO){
        Person person = new Person(personDTO.getFirstname(), personDTO.getLastname(), personDTO.getPersonnumber(),
                personDTO.getEmailaddress(), personDTO.getPassword(), 2, personDTO.getUsername());
        personRepository.save(person);
    }
}
