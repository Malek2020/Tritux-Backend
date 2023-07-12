package com.tritux.gestionSalleSport.payload.request;
import java.sql.Date;
import java.util.Set;
import javax.validation.constraints.*;

import org.springframework.format.annotation.DateTimeFormat;

public class SignupRequest {
 
    private String firstName;
    private String lastName;
    @NotBlank
    @Size(min = 3, max = 20)
    private String username;

    @NotBlank
    @Size(max = 50)
    @Email
    private String email;
    private Set<String> role;

    @NotBlank
    @Size(min = 6, max = 40)
    private String password;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date birthDate;
    private Integer phoneNumber;



    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<String> getRole() {
        return this.role;
    }

    public void setRole(Set<String> role) {
        this.role = role;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void getBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }
    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public void getphoneNumber(Integer phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    public void setphoneNumber(Integer phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
