package com.smc.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name= "CONTACT")
public class Contact {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int cId;

    private String phone;
    private String name;
    private String nickName;
    private String email;
    private String imageUrl;
    private String work;

    @Column(length = 1000)
    private String description;


    @ManyToOne
    private User user;

}
