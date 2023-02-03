package se.kth.iv1201.appserv.jobapp.service;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import se.kth.iv1201.appserv.jobapp.domain.Person;
import se.kth.iv1201.appserv.jobapp.domain.external.request.UserDTO;
import se.kth.iv1201.appserv.jobapp.domain.external.response.GenericResponse;
import se.kth.iv1201.appserv.jobapp.repository.PersonRepository;

@Service
public class UserService {

    private final PersonRepository personRepository;

    public UserService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public ResponseEntity loginUser(UserDTO userDTO) {
        Person person = personRepository.findByUsername(userDTO.getUsername());
        if(person == null) {
            System.out.println("Wrong username");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        if(!person.getPassword().equals(userDTO.getPassword())){
            System.out.println("Wrong password");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        return ResponseEntity.status(HttpStatus.OK).build();

    }
}
