package se.kth.iv1201.appserv.jobapp.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "competence_profile")
public class CompetenceProfile {

    @Id
    int competenceProfileId;
    int personId;
    int competenceId;
    double yearsOfExperience;

    public CompetenceProfile(int personId, int competenceId, double yearsOfExperience) {
        this.personId = personId;
        this.competenceId = competenceId;
        this.yearsOfExperience = yearsOfExperience;
    }
}
