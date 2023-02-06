package se.kth.iv1201.appserv.jobapp.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.kth.iv1201.appserv.jobapp.domain.external.request.UserDTO;
import se.kth.iv1201.appserv.jobapp.domain.external.response.GenericResponse;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {

   // private final UserService userService;

  //  public UserController(UserService userService) {
  //      this.userService = userService;
   // }

    @GetMapping("admin")
    public String admin(){
        return "admin can log in here";
    }
    @GetMapping("any/g")
    public String any(){
        return "Anyone can log in here";
    }

   // @PostMapping("login")
  //  public ResponseEntity loginUser(@RequestBody UserDTO userDTO){
      //  return userService.loginUser(userDTO);
    //}

}
