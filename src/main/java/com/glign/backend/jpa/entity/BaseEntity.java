package com.glign.backend.jpa.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.Date;

@Getter
@Setter(AccessLevel.PROTECTED)
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseEntity<U> {
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_date", nullable = false, updatable = false)
    @CreatedDate
    private Date createdDate;

    @Column(name = "created_by")
    @CreatedBy
    private String createdBy;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "last_modified_date")
    @LastModifiedDate
    private Date lastModifiedDate;

    @Column(name = "last_modified_by")
    @LastModifiedBy
    private String lastModifiedBy;
}
