package se.kth.iv1201.appserv.jobapp.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import se.kth.iv1201.appserv.jobapp.domain.Person;

import java.util.List;

@Repository
public interface PersonRepository extends JpaRepository<Person, Integer> {

    Person findByUsername(String username);
}
