package com.karkiayush.eventease.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

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
}
