package com.marooo.ticketmanagement.domain.member;

import com.marooo.ticketmanagement.domain.ticket.Ticket;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "member")
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String phoneNumber;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private MemberStatus status = MemberStatus.ACTIVE;

    @Column(nullable = false)
    private LocalDate createdAt;

    @OneToMany(mappedBy = "member")
    private List<Ticket> tickets;
}
