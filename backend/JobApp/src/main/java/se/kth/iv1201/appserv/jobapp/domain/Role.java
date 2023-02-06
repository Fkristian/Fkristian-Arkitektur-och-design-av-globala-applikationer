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
@Table(name = "role")
public class Role {

    @Id
    int roleId;
    String name;

    public Role(String name) {
        this.roleId = roleId;
        this.name = name;
    }
}
