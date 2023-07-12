package com.tritux.gestionSalleSport.payload.response;
import java.sql.Date;
import java.util.List;

public class UserInfoResponse {
    private Long id;
    private String firstName;
    private String lastName;
    private String username;
    private Date birthDate;
    private Integer phoneNumber;
    private String email;
    private List<String> roles;

     public UserInfoResponse(Long id,String firstName,String lastName, String username, String email, List<String> roles, Date birthDate, Integer phoneNumber) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.email = email;
        this.roles = roles;
        this.birthDate = birthDate;
        this.phoneNumber = phoneNumber;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id){
        this.id = id;
    }
     public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<String> getRoles() {
        return roles;
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
     public void getPhoneNumber(Integer phoneNumber) {
         this.phoneNumber = phoneNumber;
     }
     public void setPhoneNumber(Integer phoneNumber) {
         this.phoneNumber = phoneNumber;
     }
}
