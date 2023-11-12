package com.marooo.ticketmanagement.controller.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

public class MemberRequestDto {

    @Schema(description = "회원 생성 DTO")
    @Builder
    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    public static class MemberCreateDto {
        private String name;
        private String phoneNumber;
    }
}
