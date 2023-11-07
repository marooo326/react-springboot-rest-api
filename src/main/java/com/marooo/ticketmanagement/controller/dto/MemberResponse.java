package com.marooo.ticketmanagement.controller.dto;

import com.marooo.ticketmanagement.domain.member.MemberStatus;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

public class MemberResponse {

    @Builder
    @Getter
    public static class Info {
        private Long id;
        private String name;
        private String phoneNumber;
        private MemberStatus status;
        private LocalDate createdAt;
    }
}
