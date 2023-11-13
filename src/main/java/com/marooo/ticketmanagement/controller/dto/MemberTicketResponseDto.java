package com.marooo.ticketmanagement.controller.dto;

import com.marooo.ticketmanagement.domain.mapping.memberTicket.MemberTicketState;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

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
        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;
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
        private TicketResponseDto.TicketDetailDto ticket;
        private Integer useCount;
        private Integer leftCount;
        private LocalDate startDate;
        private LocalDate expireDate;
        private MemberTicketState state;
        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;
    }
}
