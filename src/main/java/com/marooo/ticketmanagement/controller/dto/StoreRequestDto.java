package com.marooo.ticketmanagement.controller.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class StoreRequestDto {

    @Schema(description = "가게 생성 DTO")
    @Builder
    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    public static class StoreCreateDto {
        @Size(min = 2, max = 10, message = "이름은 2자 이상 10자 이하로 입력해주세요.")
        private String name;
        @Pattern(regexp = "^010-\\d{4}-\\d{4}$", message = "번호 형식이 유효하지 않습니다. (예: 010-1234-1234)")
        private String phoneNumber;
        private String description;
    }
}
