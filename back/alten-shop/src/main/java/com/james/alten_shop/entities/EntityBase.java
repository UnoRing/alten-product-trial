package com.james.alten_shop.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.ZonedDateTimeSerializer;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.ZonedDateTime;

@Data
@MappedSuperclass
// Probably overkill for 1 entity but hey at least it's clean overkill
public class EntityBase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @JsonSerialize(using = ZonedDateTimeSerializer.class)
    @CreatedDate
    @Column(name = "created_at")
    @JsonIgnore
    private ZonedDateTime createdAt;

    @JsonSerialize(using = ZonedDateTimeSerializer.class)
    @Column(name = "updated_at")
    @LastModifiedDate
    private ZonedDateTime updatedAt;

    /** Sets the creation and update date before persistence. */
    @PrePersist
    public void prePersist() {
        ZonedDateTime now = ZonedDateTime.now();
        this.createdAt = now;
        this.updatedAt = now;
    }

    /** Sets the update date. */
    @PreUpdate
    public void preUpdate() {
        this.updatedAt = ZonedDateTime.now();
    }
}
