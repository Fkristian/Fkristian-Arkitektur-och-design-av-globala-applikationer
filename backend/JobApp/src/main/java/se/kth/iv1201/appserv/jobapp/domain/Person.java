package se.kth.iv1201.appserv.jobapp.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Person {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="person_id")
    int personId;
    String name;
    String surname;
    String pnr;
    String email;
    String password;
    int role_id;
    String username;

    protected Person() {}


    public Person(String name, String surname, String pnr, String email, String password, int role_id, String username) {
        this.name = name;
        this.surname = surname;
        this.pnr = pnr;
        this.email = email;
        this.password = password;
        this.role_id = role_id;
        this.username = username;
    }

    public int getPersonId() {
        return personId;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getPnr() {
        return pnr;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public int getRole_id() {
        return role_id;
    }

    public String getUsername() {
        return username;
    }

}
