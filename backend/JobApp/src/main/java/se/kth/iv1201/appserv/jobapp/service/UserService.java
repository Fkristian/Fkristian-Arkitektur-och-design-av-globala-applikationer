package se.kth.iv1201.appserv.jobapp.service;

import org.springframework.http.HttpStatus;
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

    public GenericResponse loginUser(UserDTO userDTO) {
        Person person = personRepository.findByUsername(userDTO.getUsername());
        if(person == null) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "User was not found");
        }
        if(!person.getPassword().equals(userDTO.getPassword())){
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Password missmatch");
        }
        return GenericResponse.OK;

    }
}
