package com.marooo.ticketmanagement.domain.ticket;

import com.marooo.ticketmanagement.domain.member.Member;
import com.marooo.ticketmanagement.domain.ticketTemplate.TicketTemplate;

import javax.persistence.*;

@Entity
@Table(name = "ticket")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "DTYPE", discriminatorType = DiscriminatorType.STRING)
public abstract class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne
    @JoinColumn(name = "template_id")
    private TicketTemplate template;
}
