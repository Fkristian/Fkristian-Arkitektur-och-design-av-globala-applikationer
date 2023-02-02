package se.kth.iv1201.appserv.jobapp.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import se.kth.iv1201.appserv.jobapp.domain.external.response.GenericResponse;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("api/users")
public class UserController {
    @GetMapping
    public GenericResponse getUsers(){
        return GenericResponse.OK;
    }
}
