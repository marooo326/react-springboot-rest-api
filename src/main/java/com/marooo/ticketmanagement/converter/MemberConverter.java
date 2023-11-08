package com.marooo.ticketmanagement.converter;

import com.marooo.ticketmanagement.controller.dto.MemberRequestDto;
import com.marooo.ticketmanagement.controller.dto.MemberResponseDto;
import com.marooo.ticketmanagement.domain.member.Member;
import org.springframework.stereotype.Component;

@Component
public class MemberConverter {

    public static Member toMember(MemberRequestDto.CreateDto createDto) {
        return Member.builder()
                .name(createDto.getName())
                .phoneNumber(createDto.getPhoneNumber())
                .build();
    }

    public static MemberResponseDto.DetailDto toDetailDto(Member member) {
        return MemberResponseDto.DetailDto.builder()
                .id(member.getId())
                .name(member.getName())
                .phoneNumber(member.getPhoneNumber())
                .status(member.getStatus())
                .createdAt(member.getCreatedAt())
                .build();
    }
}
