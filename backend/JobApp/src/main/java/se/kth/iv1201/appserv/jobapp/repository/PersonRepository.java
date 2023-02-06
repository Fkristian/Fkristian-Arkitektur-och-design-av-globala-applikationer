package se.kth.iv1201.appserv.jobapp.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import se.kth.iv1201.appserv.jobapp.domain.User;

import java.util.List;

public interface PersonRepository extends JpaRepository<User, Integer> {

    Optional<User> findByUsername(String email);

}
