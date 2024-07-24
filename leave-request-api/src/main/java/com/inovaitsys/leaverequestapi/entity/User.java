package com.inovaitsys.leaverequestapi.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "system_user")
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;
}
