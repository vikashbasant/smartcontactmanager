package com.smc.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name = "USER")
public class User {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @NotBlank(message = "Name can't be Blank Must Required!")
    @Size(min = 2, max = 22, message = "User name must be in the range of 2-22")
    private String name;

    @NotBlank(message = "Email can't be Blank Must Required!")
    @Email(regexp = "^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$", message = "User Email must be Proper order!")
    private String email;

    @NotBlank(message = "Password can't be Blank Must Required!")
    @Size(min=8, max=15, message = "User password must be in the range of 8-15")
    private String password;

    private boolean enabled;
    private String role;
    private String imageUrl;

    @Column(length = 500)
    private String about;


    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "user")
    private List<Contact> contacts = new ArrayList<Contact> ();
}
