package com.marooo.ticketmanagement.domain.store;

import javax.persistence.*;
import java.time.LocalDate;

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
}
