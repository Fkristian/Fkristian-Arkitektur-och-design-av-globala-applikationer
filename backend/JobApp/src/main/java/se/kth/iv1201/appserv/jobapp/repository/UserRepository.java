package se.kth.iv1201.appserv.jobapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import se.kth.iv1201.appserv.jobapp.domain.User;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    User findByUsername(String username);

    User findByPersonId(int id);

    @Query("select u from User u inner join ApplicationStatus app on u.personId = app.personId "+
            "where not app.status = 'unapplied'")
    List<User> findByRoleId(Integer id);

}
