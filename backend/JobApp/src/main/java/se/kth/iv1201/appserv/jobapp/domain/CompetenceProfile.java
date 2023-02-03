package se.kth.iv1201.appserv.jobapp.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class CompetenceProfile {

    @Id
    int competenceProfileId;
    int personId;
    int competenceId;
    double yearsOfExperience;

    protected CompetenceProfile(){}

    public CompetenceProfile(int personId, int competenceId, double yearsOfExperience) {
        this.personId = personId;
        this.competenceId = competenceId;
        this.yearsOfExperience = yearsOfExperience;
    }

    public int getCompetenceProfileId() {
        return competenceProfileId;
    }

    public int getPersonId() {
        return personId;
    }

    public int getCompetenceId() {
        return competenceId;
    }

    public double getYearsOfExperience() {
        return yearsOfExperience;
    }
}
