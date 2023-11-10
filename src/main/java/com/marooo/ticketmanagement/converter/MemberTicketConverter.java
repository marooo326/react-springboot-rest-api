package com.marooo.ticketmanagement.converter;

import com.marooo.ticketmanagement.controller.dto.MemberTicketResponseDto;
import com.marooo.ticketmanagement.domain.mapping.memberTicket.MemberMultiUseTicket;
import com.marooo.ticketmanagement.domain.mapping.memberTicket.MemberSubscriptionTicket;
import com.marooo.ticketmanagement.domain.mapping.memberTicket.MemberTicket;
import com.marooo.ticketmanagement.domain.mapping.memberTicket.MemberTicketState;
import com.marooo.ticketmanagement.domain.member.Member;
import com.marooo.ticketmanagement.domain.ticket.MultiUseTicket;
import com.marooo.ticketmanagement.domain.ticket.SubscriptionTicket;
import com.marooo.ticketmanagement.domain.ticket.Ticket;
import com.marooo.ticketmanagement.domain.ticket.TicketType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
@RequiredArgsConstructor
public class MemberTicketConverter {
    private static final MemberConverter memberConverter = new MemberConverter();
    private static final TicketConverter ticketConverter = new TicketConverter();

    public static MemberTicket toMemberTicket(Member member, Ticket ticket) {
        final TicketType ticketType = ticket.getTicketType();
        if (ticketType == TicketType.MULTI_USE) {
            return MemberMultiUseTicket.builder()
                    .state(MemberTicketState.AVAILABLE)
                    .member(member)
                    .ticket(ticket)
                    .useCount(0)
                    .leftCount(((MultiUseTicket) ticket).getUseLimit())
                    .build();
        } else if (ticketType == TicketType.SUBSCRIPTION) {
            final LocalDate startDate = LocalDate.now();
            final LocalDate expireDate = startDate.plusDays(((SubscriptionTicket) ticket).getValidDays());
            return MemberSubscriptionTicket.builder()
                    .state(MemberTicketState.AVAILABLE)
                    .member(member)
                    .ticket(ticket)
                    .startDate(startDate)
                    .expireDate(expireDate)
                    .build();
        } else {
            throw new IllegalArgumentException("TicketType is not valid");
        }
    }

    public static MemberTicketResponseDto.SummaryDto toSummaryDto(MemberTicket memberTicket) {
        return MemberTicketResponseDto.SummaryDto.builder()
                .memberTicketId(memberTicket.getId())
                .memberId(memberTicket.getMember().getId())
                .ticketId(memberTicket.getTicket().getId())
                .state(memberTicket.getState())
                .build();
    }

    public static MemberTicketResponseDto.DetailDto toDetailDto(MemberTicket memberTicket) {
        return MemberTicketResponseDto.DetailDto.builder()
                .memberTicketId(memberTicket.getId())
                .memberId(memberTicket.getMember().getId())
                .ticketId(memberTicket.getTicket().getId())
                .useCount(memberTicket instanceof MemberMultiUseTicket ? ((MemberMultiUseTicket) memberTicket).getUseCount() : null)
                .leftCount(memberTicket instanceof MemberMultiUseTicket ? ((MemberMultiUseTicket) memberTicket).getLeftCount() : null)
                .startDate(memberTicket instanceof MemberSubscriptionTicket ? ((MemberSubscriptionTicket) memberTicket).getStartDate().toString() : null)
                .expireDate(memberTicket instanceof MemberSubscriptionTicket ? ((MemberSubscriptionTicket) memberTicket).getExpireDate().toString() : null)
                .state(memberTicket.getState())
                .ticket(ticketConverter.toDetailDto(memberTicket.getTicket()))
                .build();
    }
}
