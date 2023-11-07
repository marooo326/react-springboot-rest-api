package com.marooo.ticketmanagement.domain.ticketTemplate;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "ticket")
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
}
