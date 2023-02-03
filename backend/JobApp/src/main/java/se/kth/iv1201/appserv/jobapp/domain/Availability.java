package se.kth.iv1201.appserv.jobapp.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.sql.Date;

@Entity
public class Availability {
    @Id
    int availabilityId;
    int personId;
    Date fromDate;
    Date toDate;

    protected Availability(){}

    public Availability(int personId, Date fromDate, Date toDate) {
        this.personId = personId;
        this.fromDate = fromDate;
        this.toDate = toDate;
    }

    public int getAvailabilityId() {
        return availabilityId;
    }

    public int getPersonId() {
        return personId;
    }

    public Date getFromDate() {
        return fromDate;
    }

    public Date getToDate() {
        return toDate;
    }
}
