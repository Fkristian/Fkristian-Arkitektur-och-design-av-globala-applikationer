package se.kth.iv1201.appserv.jobapp.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.sql.Date;

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
@Table(name = "availability")
public class Availability {
    @Id
    int availabilityId;
    int personId;
    Date fromDate;
    Date toDate;

    public Availability(int personId, Date fromDate, Date toDate) {
        this.personId = personId;
        this.fromDate = fromDate;
        this.toDate = toDate;
    }

}
