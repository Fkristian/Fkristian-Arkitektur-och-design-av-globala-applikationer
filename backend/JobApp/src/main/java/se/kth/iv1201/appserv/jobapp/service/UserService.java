package se.kth.iv1201.appserv.jobapp.service;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import se.kth.iv1201.appserv.jobapp.domain.Role;
import se.kth.iv1201.appserv.jobapp.domain.User;
import se.kth.iv1201.appserv.jobapp.domain.external.request.RegisterRequest;
import se.kth.iv1201.appserv.jobapp.domain.external.request.LogInRequest;
import se.kth.iv1201.appserv.jobapp.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public ResponseEntity registerUser(RegisterRequest registerRequest){
        if(userRepository.findByUsername(registerRequest.getUsername()) == null) {
            Role role = new Role(2, "applicant");
            User user = new User(registerRequest.getFirstname(), registerRequest.getLastname(), registerRequest.getPersonnumber(),
                    registerRequest.getEmailaddress(), registerRequest.getPassword(), role, registerRequest.getUsername(), 2);
            userRepository.save(user);
            return ResponseEntity.status(HttpStatus.OK).build();
        }
        else {
            return ResponseEntity.status(HttpStatusCode.valueOf(409)).build();
        }
    }
    @Transactional
    public ResponseEntity loginUser(LogInRequest logInRequest) {
        User user = userRepository.findByUsername(logInRequest.getUsername());
        if(user == null) {
            System.out.println("Wrong username");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        if(!user.getPassword().equals(logInRequest.getPassword())){
            System.out.println("Wrong password");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }
}
