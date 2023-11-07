package com.marooo.ticketmanagement.domain.ticketTemplate;

import com.marooo.ticketmanagement.domain.store.Store;
import com.marooo.ticketmanagement.domain.ticket.Ticket;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "ticket_template")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "DTYPE", discriminatorType = DiscriminatorType.STRING)
public abstract class TicketTemplate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column
    private String description;

    @Column(nullable = false)
    private LocalDate createdAt;

    @ManyToOne
    @JoinColumn(name = "ticket_template_id", nullable = false)
    private Store store;

    @OneToMany(mappedBy = "template", cascade = CascadeType.ALL)
    private List<Ticket> ticket;
}
