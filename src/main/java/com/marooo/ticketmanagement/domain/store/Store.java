package com.marooo.ticketmanagement.domain.store;

import com.marooo.ticketmanagement.domain.BaseEntity;
import com.marooo.ticketmanagement.domain.ticket.Ticket;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "store")
@Builder
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Store extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String phoneNumber;

    @Column
    private String description;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "store", cascade = CascadeType.ALL)
    private List<Ticket> tickets = new ArrayList<>();
}
