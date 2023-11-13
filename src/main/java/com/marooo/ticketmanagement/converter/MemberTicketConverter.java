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
import com.marooo.ticketmanagement.exception.ErrorMessage;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MemberTicketConverter {

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
            throw new IllegalArgumentException(ErrorMessage.TICKET_TYPE_NOT_SUPPORTED.getMessage());
        }
    }

    public static MemberTicketResponseDto.MemberTicketSummaryDto toSummaryDto(MemberTicket memberTicket) {
        return MemberTicketResponseDto.MemberTicketSummaryDto.builder()
                .memberTicketId(memberTicket.getId())
                .memberId(memberTicket.getMember().getId())
                .ticketId(memberTicket.getTicket().getId())
                .state(memberTicket.getState())
                .createdAt(memberTicket.getCreatedAt())
                .updatedAt(memberTicket.getUpdatedAt())
                .build();
    }

    public static MemberTicketResponseDto.MemberTicketDetailDto toDetailDto(MemberTicket memberTicket) {
        final Ticket ticket = memberTicket.getTicket();
        final Member member = memberTicket.getMember();
        if (ticket.getTicketType() == TicketType.MULTI_USE) {
            return MemberTicketResponseDto.MemberTicketDetailDto.builder()
                    .memberTicketId(memberTicket.getId())
                    .memberId(member.getId())
                    .ticketId(ticket.getId())
                    .ticket(TicketConverter.toDetailDto(ticket))
                    .useCount(((MemberMultiUseTicket) memberTicket).getUseCount())
                    .leftCount(((MemberMultiUseTicket) memberTicket).getLeftCount())
                    .state(memberTicket.getState())
                    .createdAt(memberTicket.getCreatedAt())
                    .updatedAt(memberTicket.getUpdatedAt())
                    .build();
        } else if (ticket.getTicketType() == TicketType.SUBSCRIPTION) {
            return MemberTicketResponseDto.MemberTicketDetailDto.builder()
                    .memberTicketId(memberTicket.getId())
                    .memberId(member.getId())
                    .ticketId(ticket.getId())
                    .ticket(TicketConverter.toDetailDto(ticket))
                    .startDate(((MemberSubscriptionTicket) memberTicket).getStartDate())
                    .expireDate(((MemberSubscriptionTicket) memberTicket).getExpireDate())
                    .state(memberTicket.getState())
                    .createdAt(memberTicket.getCreatedAt())
                    .updatedAt(memberTicket.getUpdatedAt())
                    .build();
        } else {
            throw new IllegalArgumentException(ErrorMessage.TICKET_TYPE_NOT_SUPPORTED.getMessage());
        }
    }
}
