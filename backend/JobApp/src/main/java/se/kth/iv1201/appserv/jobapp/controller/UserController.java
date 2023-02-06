package se.kth.iv1201.appserv.jobapp.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import se.kth.iv1201.appserv.jobapp.domain.User;
import se.kth.iv1201.appserv.jobapp.domain.external.request.RegisterRequest;
import se.kth.iv1201.appserv.jobapp.domain.external.request.LogInRequest;
import se.kth.iv1201.appserv.jobapp.service.UserService;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    public List<User> getAllUsers(){
        return userService.getAllUsers();
    }
    @PostMapping("login")
    public ResponseEntity loginUser(@RequestBody LogInRequest logInRequest){
        return userService.loginUser(logInRequest);
    }

    @PostMapping("register")
    public ResponseEntity registerUser(@RequestBody RegisterRequest registerRequest){
        return userService.registerUser(registerRequest);

    }

}
