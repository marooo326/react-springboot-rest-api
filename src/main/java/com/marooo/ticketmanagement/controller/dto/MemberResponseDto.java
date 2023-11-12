package com.marooo.ticketmanagement.controller.dto;

import com.marooo.ticketmanagement.domain.member.MemberState;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

public class MemberResponseDto {

    @Schema(description = "회원 세부 정보 DTO")
    @Builder
    @Getter
    public static class MemberDetailDto {
        private Long id;
        private String name;
        private String phoneNumber;
        private MemberState status;
        private LocalDateTime createdAt;
    }
}
