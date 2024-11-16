package com.lebonstyle.userservice.entity;

import jakarta.persistence.*;

@Entity
public class UserCredentials {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(nullable = false)
    private String hashedPassword;

    private Role role; // Example: ADMIN, User, etc.

    @OneToOne
    @JoinColumn(name = "id", referencedColumnName = "id")
    private User user;
}
