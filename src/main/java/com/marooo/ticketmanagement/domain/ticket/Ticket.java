package com.marooo.ticketmanagement.domain.ticket;

import com.marooo.ticketmanagement.domain.memberTicket.MemberTicket;
import com.marooo.ticketmanagement.domain.store.Store;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "ticket")
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column
    private String description;

    @Column
    private Integer useLimit;

    @Column
    private Integer validDays;

    @Column(nullable = false)
    private TicketType ticketType;

    @Column(nullable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    @ManyToOne
    @JoinColumn(name = "store_id", nullable = false)
    private Store store;

    @OneToMany(mappedBy = "ticket", cascade = CascadeType.ALL)
    private List<MemberTicket> memberTickets;
}
