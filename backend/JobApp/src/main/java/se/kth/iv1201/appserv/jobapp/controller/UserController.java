package se.kth.iv1201.appserv.jobapp.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @GetMapping("/any")
    public String home(){
        return "Everyone can get in";
    }

    @GetMapping("/user")
    public String user(){
        return "Users can get in";
    }

    @GetMapping("/admin")
    public String admin(){
        return "Admins can get in";
    }


}
