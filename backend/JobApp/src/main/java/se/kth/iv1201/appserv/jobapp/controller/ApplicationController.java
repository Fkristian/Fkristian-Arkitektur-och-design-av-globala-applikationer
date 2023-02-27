package se.kth.iv1201.appserv.jobapp.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import se.kth.iv1201.appserv.jobapp.domain.User;
import se.kth.iv1201.appserv.jobapp.domain.external.request.ApplicationRequest;
import se.kth.iv1201.appserv.jobapp.domain.external.request.StatusRequst;
import se.kth.iv1201.appserv.jobapp.service.ApplicationService;

import java.util.List;

/**
 * Class responsible to communicate with the Front End with
 * requests related to application management.
 */
@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/application")
@AllArgsConstructor
public class ApplicationController {

    private final ApplicationService applicationService;

    /**
     * Method to retrieve all current applications in the database to the Front End.
     *
     * @return All current users as a JSON-object.
     */
    @GetMapping("all")
    public ResponseEntity <List <User>> getAllApplications(){
        return ResponseEntity.ok(applicationService.getAllApplications());
    }

    /**
     * Method to retrieve one specific application to the Front End from the database.
     *
     * @param id the integer ID used to find the application.
     * @return the specified application as a JSON-object.
     */
    @GetMapping("{id}")
    public ResponseEntity <User> getApplicationById(@PathVariable int id){

        return ResponseEntity.ok(applicationService.getApplicationById(id));
    }

    /**
     * Method used to update an application from the Front End in the database.
     * @param statusRequest the request-body containing information about the application status.
     * @return an HTTP-status code to inform the Front End how the transaction went.
     */
    @PutMapping("update-status")
    public ResponseEntity <?> updateApplicationStatus(@RequestBody StatusRequst statusRequest){

        return applicationService.updateApplicationStatus(statusRequest);
    }

    /**
     * Method used to post a new application from the Front End to the database.
     *
     * @param applicationRequest the request-body containing all the application information.
     * @param request session-object used to retrieve the authentication token with user information on it.
     * @return an HTTP-status code to inform the Front End how the transaction went.
     */
    @PostMapping("post")
    public ResponseEntity <?> postApplication(@RequestBody ApplicationRequest applicationRequest, HttpServletRequest request){
        return applicationService.postApplication(applicationRequest, request);
    }

}
