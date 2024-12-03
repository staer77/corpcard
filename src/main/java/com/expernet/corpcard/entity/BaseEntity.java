package com.expernet.corpcard.entity;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class BaseEntity {
    @CreationTimestamp
    @Column(name = "CREATED_AT")
    private Timestamp createdAt;
    @UpdateTimestamp
    @Column(name = "UPDATED_AT")
    private Timestamp updatedAt;
}
