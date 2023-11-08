package com.marooo.ticketmanagement.domain.memberTicket;

import com.marooo.ticketmanagement.domain.member.Member;
import com.marooo.ticketmanagement.domain.ticket.Ticket;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "member_ticket")
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class MemberTicket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Integer useCount;

    @Column
    private LocalDate startDate;

    @Column(nullable = false)
    private MemberTicketState state;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne
    @JoinColumn(name = "ticket_id")
    private Ticket ticket;
}
