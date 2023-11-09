package com.marooo.ticketmanagement.controller.dto;

import lombok.*;

public class MemberTicketRequestDto {

    @Builder
    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    public static class AssignDto {
        private Long memberId;
        private Long ticketId;
    }
}
