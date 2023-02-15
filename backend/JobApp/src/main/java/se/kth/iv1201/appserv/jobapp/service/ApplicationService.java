package se.kth.iv1201.appserv.jobapp.service;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import se.kth.iv1201.appserv.jobapp.domain.Availability;
import se.kth.iv1201.appserv.jobapp.domain.Competence;
import se.kth.iv1201.appserv.jobapp.domain.CompetenceProfile;
import se.kth.iv1201.appserv.jobapp.domain.User;
import se.kth.iv1201.appserv.jobapp.domain.external.request.ApplicationRequest;
import se.kth.iv1201.appserv.jobapp.repository.AvailabilityRepository;
import se.kth.iv1201.appserv.jobapp.repository.CompetenceProfileRepository;
import se.kth.iv1201.appserv.jobapp.repository.CompetenceRepository;
import se.kth.iv1201.appserv.jobapp.repository.UserRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class ApplicationService {

    private final UserRepository userRepository;
    private final AvailabilityRepository availabilityRepository;
    private final CompetenceRepository competenceRepository;
    private final CompetenceProfileRepository competenceProfileRepository;
    private final JwtService jwtService;
    public List<User> getAllApplications() {
        return userRepository.findByRoleId(2);
    }

    public User getApplicationById(int id){
        return userRepository.findByPersonId(id);
    }

    @Transactional
    public ResponseEntity postApplication(ApplicationRequest applicationRequest) {
        int personId = userRepository.findByUsername(jwtService.extractUsername(applicationRequest.getJwt())).getPersonId();

        var availability = Availability.builder()
                .personId(personId)
                .fromDate(applicationRequest.getFromDate())
                .toDate(applicationRequest.getToDate())
                .build();
        availabilityRepository.save(availability);

        var competence = Competence.builder()
                .name("ticketer")
                .build();
        int compId = competenceRepository.save(competence).getCompetenceId();

        var competenceProfile = CompetenceProfile.builder()
                .personId(personId)
                .competenceId(compId)
                .yearsOfExperience(0.4)
                .build();
        competenceProfileRepository.save(competenceProfile);

        return ResponseEntity.ok().build();
    }
}
