package se.kth.iv1201.appserv.jobapp.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import se.kth.iv1201.appserv.jobapp.domain.User;
import se.kth.iv1201.appserv.jobapp.domain.external.request.ApplicationRequest;
import se.kth.iv1201.appserv.jobapp.service.ApplicationService;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/application/")
public class ApplicationController {

    private final ApplicationService applicationService;

    public ApplicationController(ApplicationService applicationService) {
        this.applicationService = applicationService;
    }

    @GetMapping("all")
    public ResponseEntity <List <User>> getAllApplications(){
        return ResponseEntity.ok(applicationService.getAllApplications());
    }
    @GetMapping("{id}")
    public ResponseEntity <User> getApplicationById(@PathVariable int id){
        return ResponseEntity.ok(applicationService.getApplicationById(id));
    }
    @PostMapping("post")
    public ResponseEntity postApplication(@RequestBody ApplicationRequest applicationRequest){
        return applicationService.postApplication(applicationRequest);
    }

}
