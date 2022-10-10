package com.example.securitydemo.entity;

import lombok.*;

import javax.persistence.*;

/**
 * @author Goncharov Aleksandr
 */
@Data
@Entity
@Table(name = "user_table")
public class User {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "username")
    private String username;

    @Column(name = "name")
    private String name;

    @Column(name = "user_password")
    private String password;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "user_role")
    private Role role;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "status")
    private Status status;

}
