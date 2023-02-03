package se.kth.iv1201.appserv.jobapp.domain.external.request;

public class PersonDTO {
    String firstname;
    String lastname;
    String emailaddress;
    String personnumber;
    String username;
    String password;

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getEmailaddress() {
        return emailaddress;
    }

    public String getPersonnumber() {
        return personnumber;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
