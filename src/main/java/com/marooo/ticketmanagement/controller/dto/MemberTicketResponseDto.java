package com.marooo.ticketmanagement.controller.dto;

import com.marooo.ticketmanagement.domain.mapping.memberTicket.MemberTicketState;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

public class MemberTicketResponseDto {

    @Schema(description = "회원-이용권 요약 정보 DTO")
    @Builder
    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    public static class MemberTicketSummaryDto {
        private Long memberTicketId;
        private Long memberId;
        private Long ticketId;
        private MemberTicketState state;
    }

    @Schema(description = "회원-이용권 세부 정보 DTO")
    @Builder
    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    public static class MemberTicketDetailDto {
        private Long memberTicketId;
        private Long memberId;
        private Long ticketId;
        private Integer useCount;
        private Integer leftCount;
        private String startDate;
        private String expireDate;
        private MemberTicketState state;
        private TicketResponseDto.TicketDetailDto ticket;
    }
}
