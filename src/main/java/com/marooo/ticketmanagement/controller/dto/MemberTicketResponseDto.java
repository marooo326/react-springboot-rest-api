package com.marooo.ticketmanagement.controller.dto;

import com.marooo.ticketmanagement.domain.mapping.memberTicket.MemberTicketState;
import lombok.*;

public class MemberTicketResponseDto {

    @Builder
    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    public static class SummaryDto {
        private Long memberTicketId;
        private Long memberId;
        private Long ticketId;
        private MemberTicketState state;
    }

    @Builder
    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    public static class DetailDto {
        private Long memberTicketId;
        private Long memberId;
        private Long ticketId;
        private Integer useCount;
        private Integer leftCount;
        private String startDate;
        private String expireDate;
        private MemberTicketState state;
        private TicketResponseDto.DetailDto ticket;
    }
}
