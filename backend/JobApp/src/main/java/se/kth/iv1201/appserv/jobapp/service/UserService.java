package se.kth.iv1201.appserv.jobapp.service;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import se.kth.iv1201.appserv.jobapp.domain.User;
import se.kth.iv1201.appserv.jobapp.domain.external.request.UserDTO;
import se.kth.iv1201.appserv.jobapp.repository.PersonRepository;

//@Service
//public class UserService {

  //  private final PersonRepository personRepository;

    //public UserService(PersonRepository personRepository) {
      //  this.personRepository = personRepository;
    //}

    //public ResponseEntity loginUser(UserDTO userDTO) {
      //  var user = personRepository.findByUsername(userDTO.getUsername());
        //if(user == null) {
          //  System.out.println("Wrong username");
            //return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        //}
        //if(!user.getPassword().equals(userDTO.getPassword())){
          //  System.out.println("Wrong password");
           // return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        //}
        //return ResponseEntity.status(HttpStatus.OK).build();

  //  }

//}
