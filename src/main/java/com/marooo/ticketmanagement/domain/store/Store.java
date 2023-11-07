package com.marooo.ticketmanagement.domain.store;

import com.marooo.ticketmanagement.domain.ticketTemplate.TicketTemplate;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "store")
public class Store {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column
    private String description;

    @Column
    private LocalDate createdAt;

    @OneToMany(mappedBy = "store", cascade = CascadeType.ALL)
    private List<TicketTemplate> tickets;
}
