package com.registration.registration.Infrastructure.Models;

import lombok.Data;
import org.springframework.data.annotation.Id;

import java.time.LocalDate;

@Data
public class Customer {
    @Id
    private long id;
    private LocalDate dob;
    private String email;
    private String firstName;
    private String lastName;
    private String phone;
    private FileInfo profilePicture;

    public Customer(LocalDate dob, String email, String firstName, String lastName, String phone, FileInfo profilePicture) {
        this.dob = dob;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.profilePicture = profilePicture;
    }
}
