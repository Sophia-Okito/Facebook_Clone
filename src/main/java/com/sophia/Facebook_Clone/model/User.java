package com.sophia.Facebook_Clone.model;

import lombok.*;
import java.sql.Timestamp;

@AllArgsConstructor
@Data
public class User {
    private int userID;
    private String firstName;
    private  String lastName;
    private String email;
    private  String password;
    private String gender;
    private String DateOfBirth;
    private Timestamp dateCreated;
    private Timestamp lastLogin;
    private String active;

    public User(String firstName, String lastName, String email, String password, String gender,
                String DateOfBirth) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.gender = gender;
        this.DateOfBirth = DateOfBirth;
    }
}
