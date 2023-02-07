package se.kth.iv1201.appserv.jobapp.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import se.kth.iv1201.appserv.jobapp.domain.Role;
import se.kth.iv1201.appserv.jobapp.domain.User;
import se.kth.iv1201.appserv.jobapp.domain.external.request.LogInRequest;
import se.kth.iv1201.appserv.jobapp.domain.external.request.RegisterRequest;
import se.kth.iv1201.appserv.jobapp.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;

    public Object register(RegisterRequest request) {
        Role role = new Role(2, "applicant");
        var user = User.builder()
                .name(request.getFirstname())
                .email(request.getEmailaddress())
                .password(passwordEncoder.encode(request.getPassword()))
                .pnr(request.getPersonnumber())
                .role(role)
                .surname(request.getLastname())
                .username(request.getUsername())
                .build();
                repository.save(user);

        return null;
    }

    public Object authenticate(LogInRequest request) {
        return null;
    }


}
