package com.karkiayush.eventease.domain.entity;

import com.karkiayush.eventease.domain.enums.TicketStatusEnum;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "tickets")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Ticket {
    @Id
    @Column(name = "id", nullable = false, unique = true)
    private UUID id;

    @Column(name = "ticketstatus", nullable = false)
    @Enumerated(EnumType.STRING)
    private TicketStatusEnum status;

    @Column(name = "ticket", nullable = false)
    private LocalDateTime ticketCreationDateTime;
}
