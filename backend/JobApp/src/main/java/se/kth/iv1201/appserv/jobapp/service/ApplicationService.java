package se.kth.iv1201.appserv.jobapp.service;

import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import se.kth.iv1201.appserv.jobapp.domain.ApplicationStatus;
import se.kth.iv1201.appserv.jobapp.domain.Availability;
import se.kth.iv1201.appserv.jobapp.domain.Competence;
import se.kth.iv1201.appserv.jobapp.domain.CompetenceProfile;
import se.kth.iv1201.appserv.jobapp.domain.User;
import se.kth.iv1201.appserv.jobapp.domain.external.request.ApplicationRequest;
import se.kth.iv1201.appserv.jobapp.domain.external.request.StatusRequst;
import se.kth.iv1201.appserv.jobapp.domain.external.response.GenericResponse;
import se.kth.iv1201.appserv.jobapp.domain.internal.Competences;
import se.kth.iv1201.appserv.jobapp.domain.internal.Dates;
import se.kth.iv1201.appserv.jobapp.repository.ApplicationStatusRepository;
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
    private final ApplicationStatusRepository applicationStatusRepository;
    public List<User> getAllApplications() {
        return userRepository.findByRoleId(2);
    }

    public User getApplicationById(int id){
        return userRepository.findByPersonId(id);
    }
    @Transactional
    public ResponseEntity postApplication(ApplicationRequest applicationRequest, @NonNull HttpServletRequest httpServletRequest) {

        final String authHeader = httpServletRequest.getHeader("Authorization");
        final String jwt;
        final String username;
        jwt = authHeader.substring(7);
        username = jwtService.extractUsername(jwt);
        User user = userRepository.findByUsername(username);

        for (Dates dates : applicationRequest.getDates()) {
            var availability = Availability.builder()
                    .personId(user.getPersonId())
                    .fromDate(dates.getFromDate())
                    .toDate(dates.getToDate())
                    .build();
            availabilityRepository.save(availability);
        }

        for (Competences competences: applicationRequest.getCompetences()) {
        var competence = Competence.builder()
                .name(competences.getName())
                .build();
        int compId = competenceRepository.save(competence).getCompetenceId();

        var competenceProfile = CompetenceProfile.builder()
                .personId(user.getPersonId())
                .competenceId(compId)
                .yearsOfExperience(competences.getYearsOfExperience())
                .build();
        competenceProfileRepository.save(competenceProfile);
        }

        return ResponseEntity.ok().build();
    }

    @Transactional
    public GenericResponse updateApplicationStatus(StatusRequst statusRequst) {
        ApplicationStatus status = applicationStatusRepository.findByPersonId(statusRequst.getPersonId());
        if(status == null){
            status = ApplicationStatus.builder()
                    .personId(statusRequst.getPersonId())
                    .status(statusRequst.getStatus())
                    .build();
            applicationStatusRepository.save(status);
            return GenericResponse.OK;
        }else{
            status.setStatus(statusRequst.getStatus());
            applicationStatusRepository.save(status);
            return GenericResponse.OK;
        }

    }
}
