package com.glign.backend.jpa.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@Table(name = "users")
public class User extends BaseEntity<User> {
    @Id
    @SequenceGenerator(name = "AC_AUTHORITY_SEQ", sequenceName = "ac.AC_AUTHORITY_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "AC_AUTHORITY_SEQ")
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    @Column(name = "uuid", updatable = false, nullable = false)
    private UUID uuid = UUID.randomUUID();

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "user_id")
    private List<Phone> phones;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "last_login")
    private Date lastLogin;

    @Column(name = "token")
    private String token;

    @Column(name = "is_active")
    private boolean isActive;
}
