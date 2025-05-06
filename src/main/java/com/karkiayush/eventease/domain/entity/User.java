package com.karkiayush.eventease.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "users")
/* When we use @Data, Lombok automatically generates getter, setter, AllArgsConstructor, NoArgsConstructor, ToString & EqualsAndHashCode method.
But since, we're going to implement bidirectional relationships. If we use @Data annotation, it generates EqualsAndHashCode for us, and in that case if the bidirectional relation exists, it will lead to the StackOverflow Issue. */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
    @Id
    @Column(name = "id", updatable = false, nullable = false, unique = true)
    private UUID id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    // TO DO: Organizer manages Event
    // TO DO: Staff works at Event
    // TO DO: Attendee attends Event

    // CreatedDate annotation is part of Spring Data JPA which automatically populate a field with the timestamp when the entity was persisted into the DB.
    @CreatedDate
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;
}

/*The same User represents the Organizer, Staff & Attendee.
*
* The User's permission is modeled using roles which we later on assign in keycloak.
*/
