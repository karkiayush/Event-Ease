package com.karkiayush.eventease.domain.entity;

import com.karkiayush.eventease.domain.enums.TicketStatusEnum;
import jakarta.persistence.Column;
import jakarta.persistence.Id;

import java.time.LocalDateTime;
import java.util.UUID;

public class Ticket {
    @Id
    @Column(name = "id", nullable = false, unique = true)
    private UUID id;

    @Column(name = "ticketstatus", nullable = false)
    private TicketStatusEnum status;

    @Column(name = "ticket", nullable = false)
    private LocalDateTime ticketCreationDateTime;
}
