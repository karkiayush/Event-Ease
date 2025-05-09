package com.karkiayush.eventease.domain.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Fetch;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "ticket_types")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class TicketType {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", unique = true, nullable = false, updatable = false)
    private UUID id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "total_available")
    private Integer totalAvailableTicket;

    @Column(name = "price", nullable = false)
    private double price;

    /*<--------For the Event & TicketType(Ticket Type is associated with Events)------>*/
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "event_id")
    private Event event;

    /*<--------------------Relation between TicketType-Ticket(One to Many)------------>*/
    @OneToMany(
            mappedBy = "ticketType", cascade = CascadeType.ALL,
            // orphanRemoval=true ensures that whenever a child entity is removed from Parent collection, it should also be deleted from the database
            // here, if we remove ticket from tickets list, it also removes the ticket from ticket_type database
            orphanRemoval = true
    )
    private final List<Ticket> tickets = new ArrayList<>();

    @CreatedDate
    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

}
