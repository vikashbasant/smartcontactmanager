package com.smc.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;


@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
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

    private String image;
    private String work;

    @Column(length = 1000)
    private String description;

    @ManyToOne
    private User user;

}
