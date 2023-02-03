package se.kth.iv1201.appserv.jobapp.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Competence {

    @Id
    int competenceId;
    String name;

    protected Competence(){}

    public Competence(String name) {
        this.name = name;
    }

    public int getCompetenceId() {
        return competenceId;
    }

    public String getName() {
        return name;
    }
}
