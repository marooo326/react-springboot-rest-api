package com.marooo.ticketmanagement.converter;

import com.marooo.ticketmanagement.controller.dto.MemberRequestDto;
import com.marooo.ticketmanagement.controller.dto.MemberResponseDto;
import com.marooo.ticketmanagement.domain.member.Member;
import com.marooo.ticketmanagement.domain.member.MemberState;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;

@Component
public class MemberConverter {

    public static Member toMember(MemberRequestDto.MemberCreateDto memberCreateDto) {
        return Member.builder()
                .name(memberCreateDto.getName())
                .phoneNumber(memberCreateDto.getPhoneNumber())
                .createdAt(LocalDateTime.now())
                .status(MemberState.ACTIVE)
                .memberTickets(new ArrayList<>())
                .build();
    }

    public static MemberResponseDto.MemberDetailDto toDetailDto(Member member) {
        return MemberResponseDto.MemberDetailDto.builder()
                .id(member.getId())
                .name(member.getName())
                .phoneNumber(member.getPhoneNumber())
                .status(member.getStatus())
                .createdAt(member.getCreatedAt())
                .build();
    }
}
