package com.marooo.ticketmanagement.domain.member;

import com.marooo.ticketmanagement.domain.BaseEntity;
import com.marooo.ticketmanagement.domain.mapping.memberTicket.MemberTicket;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "member")
@Builder
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Member extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String phoneNumber;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private MemberState status;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "member")
    private List<MemberTicket> memberTickets = new ArrayList<>();
}
