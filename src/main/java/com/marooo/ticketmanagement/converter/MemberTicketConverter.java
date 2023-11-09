package com.marooo.ticketmanagement.converter;

import com.marooo.ticketmanagement.controller.dto.MemberTicketRequestDto;
import com.marooo.ticketmanagement.domain.mapping.memberTicket.MemberMultiUseTicket;
import com.marooo.ticketmanagement.domain.mapping.memberTicket.MemberSubscriptionTicket;
import com.marooo.ticketmanagement.domain.mapping.memberTicket.MemberTicket;
import com.marooo.ticketmanagement.domain.mapping.memberTicket.MemberTicketState;
import com.marooo.ticketmanagement.domain.member.Member;
import com.marooo.ticketmanagement.domain.ticket.MultiUseTicket;
import com.marooo.ticketmanagement.domain.ticket.SubscriptionTicket;
import com.marooo.ticketmanagement.domain.ticket.Ticket;
import com.marooo.ticketmanagement.domain.ticket.TicketType;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class MemberTicketConverter {
    public static MemberTicket toMemberTicket(MemberTicketRequestDto.AssignDto assignDto, Member member, Ticket ticket) {
        final TicketType ticketType = ticket.getTicketType();
        if (ticketType == TicketType.MULTI_USE) {
            return MemberMultiUseTicket.builder()
                    .state(MemberTicketState.AVAILABLE)
                    .member(member)
                    .ticket((MultiUseTicket) ticket)
                    .useCount(0)
                    .leftCount(((MultiUseTicket) ticket).getUseLimit())
                    .build();
        } else if (ticketType == TicketType.SUBSCRIPTION) {
            final LocalDate startDate = LocalDate.now();
            final LocalDate expireDate = startDate.plusDays(((SubscriptionTicket) ticket).getValidDays());
            return MemberSubscriptionTicket.builder()
                    .state(MemberTicketState.AVAILABLE)
                    .member(member)
                    .ticket((SubscriptionTicket) ticket)
                    .startDate(startDate)
                    .expireDate(expireDate)
                    .build();
        } else {
            throw new IllegalArgumentException("TicketType is not valid");
        }
    }
}
