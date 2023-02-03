package se.kth.iv1201.appserv.jobapp.service;

import org.springframework.stereotype.Service;
import se.kth.iv1201.appserv.jobapp.domain.Person;
import se.kth.iv1201.appserv.jobapp.domain.external.request.UserDTO;
import se.kth.iv1201.appserv.jobapp.repository.PersonRepository;

@Service
public class UserService {

    private final PersonRepository personRepository;

    public UserService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public String loginUser(UserDTO userDTO) {
        Person person = personRepository.findByUsername(userDTO.getUsername());
        if(person == null) {
            throw new RuntimeException("User does not exist.");
        }
        if(!person.getPassword().equals(userDTO.getPassword())){
            throw new RuntimeException("Password mismatch.");
        }
        return "User was successfully logged in" ;

    }
}
