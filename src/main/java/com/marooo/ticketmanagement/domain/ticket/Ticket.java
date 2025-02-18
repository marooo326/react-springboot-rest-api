package com.marooo.ticketmanagement.domain.ticket;

import com.marooo.ticketmanagement.domain.BaseEntity;
import com.marooo.ticketmanagement.domain.mapping.memberTicket.MemberTicket;
import com.marooo.ticketmanagement.domain.store.Store;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "ticket")
@Getter
@SuperBuilder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "DTYPE")
public abstract class Ticket extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column
    private String description;

    @ManyToOne
    @JoinColumn(name = "store_id", nullable = false)
    private Store store;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "ticket", cascade = CascadeType.ALL)
    private List<MemberTicket> memberTickets = new ArrayList<>();

    public abstract TicketType getTicketType();
}
