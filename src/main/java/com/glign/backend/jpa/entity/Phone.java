package com.glign.backend.jpa.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "phones")
public class Phone extends BaseEntity<Phone> {
    @Id
    @SequenceGenerator(name = "AC_PHONE_SEQ", sequenceName = "ac.AC_PHONE_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "AC_PHONE_SEQ")
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    @Column(name = "phone_number", nullable = false)
    private String phoneNumber;

    @Column(name = "city_code", nullable = false)
    private String cityCode;

    @Column(name = "country_code", nullable = false)
    private String countryCode;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}
