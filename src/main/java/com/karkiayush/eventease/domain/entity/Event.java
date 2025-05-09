package com.karkiayush.eventease.domain.entity;

import com.karkiayush.eventease.domain.enums.EventStatusEnum;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@Entity
@Table(name = "events")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Event {
    @Id
    @Column(name = "id", updatable = false, nullable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "name", nullable = false, length = 50)
    private String name;

    @Column(name = "event_start", nullable = true)
    private LocalDateTime start;

    @Column(name = "event_end", nullable = true)
    private LocalDateTime end;

    @Column(name = "venue", nullable = false)
    private String venue;

    // We might not know when the sale of ticket is going to start & end
    @Column(name = "sales_start", nullable = true)
    private LocalDateTime salesStartDate;

    @Column(name = "sales_end", nullable = true)
    private LocalDateTime salesEndDate;

    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    private EventStatusEnum status;

    /*-------------For Organizer & Event--------------*/
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "organizer_id")
    private User organizer;

    /*------------For Staff & Event--------------*/
    @ManyToMany(mappedBy = "staffingEvents")
    private final List<User> staff = new ArrayList<>();

    /*------------------For Attendee & Event---------------*/
    @ManyToMany(mappedBy = "attendingEvents")
    private final List<User> attendees = new ArrayList<>();

    /*-----------------For Ticket type & Event-------------------*/
    @OneToMany(mappedBy = "event", cascade = CascadeType.ALL)
    private final List<TicketType> ticketTypes = new ArrayList<>();


    @CreatedDate
    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;
}
