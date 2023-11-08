package com.marooo.ticketmanagement.controller.dto;

import com.marooo.ticketmanagement.domain.member.MemberState;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

public class MemberResponseDto {

    @Builder
    @Getter
    public static class DetailDto {
        private Long id;
        private String name;
        private String phoneNumber;
        private MemberState status;
        private LocalDateTime createdAt;
    }
}
