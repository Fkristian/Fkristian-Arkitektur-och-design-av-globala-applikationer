package se.kth.iv1201.appserv.jobapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import se.kth.iv1201.appserv.jobapp.domain.ApplicationStatus;

public interface ApplicationStatusRepository extends JpaRepository <ApplicationStatus, Integer> {
}
