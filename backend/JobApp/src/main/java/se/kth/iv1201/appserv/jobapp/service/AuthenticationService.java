package se.kth.iv1201.appserv.jobapp.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import se.kth.iv1201.appserv.jobapp.domain.ERole;
import se.kth.iv1201.appserv.jobapp.domain.User;
import se.kth.iv1201.appserv.jobapp.domain.external.request.PersonDTO;
import se.kth.iv1201.appserv.jobapp.domain.external.request.UserDTO;
import se.kth.iv1201.appserv.jobapp.domain.external.response.AuthenticationResponse;
import se.kth.iv1201.appserv.jobapp.repository.PersonRepository;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final PasswordEncoder passwordEncoder;
    private final PersonRepository personRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    @Transactional
    public AuthenticationResponse register(PersonDTO personDTO){
        var user = User.builder()
                .name(personDTO.getFirstname())
                .surname(personDTO.getLastname())
                .email(personDTO.getEmailaddress())
                .password(passwordEncoder.encode(personDTO.getPassword()))
                .role_id(2)
                .role(ERole.USER)
                .pnr(personDTO.getPersonnumber())
                .build();
        personRepository.save(user);
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    public AuthenticationResponse authenticate(UserDTO userDTO) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        userDTO.getUsername(),
                        userDTO.getPassword()
                )
        );
        var user = personRepository.findByUsername(userDTO.getUsername())
                .orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }
}
