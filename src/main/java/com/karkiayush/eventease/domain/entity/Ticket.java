package com.karkiayush.eventease.domain.entity;

import com.karkiayush.eventease.domain.enums.TicketStatusEnum;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
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
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", nullable = false, unique = true, updatable = false)
    private UUID id;

    @Column(name = "ticket_status", nullable = false)
    @Enumerated(EnumType.STRING)
    private TicketStatusEnum status;

    @Column(name = "ticket", nullable = false)
    private LocalDateTime ticketCreationDateTime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ticket_type_id", nullable = false)
    private TicketType ticketType;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "purchaser_id", nullable = false)
    private User purchaser;

    /*-----------Relation between Ticket & TicketValidation Entity(One to Many)-------------*/
    @OneToMany(mappedBy = "ticket", cascade = CascadeType.ALL)
    private final List<TicketValidation> validations = new ArrayList<>();

    /*-------------Relation between Ticket & QrCode Entity------------------*/
    @OneToMany(mappedBy = "ticket", cascade = CascadeType.ALL)
    private final List<QrCode> qrCodes = new ArrayList<>();

    @CreatedDate
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;
}
