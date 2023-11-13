package com.marooo.ticketmanagement.converter;

import com.marooo.ticketmanagement.controller.dto.MemberRequestDto;
import com.marooo.ticketmanagement.controller.dto.MemberResponseDto;
import com.marooo.ticketmanagement.domain.member.Member;
import com.marooo.ticketmanagement.domain.member.MemberState;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MemberConverter {

    public static Member toMember(MemberRequestDto.MemberCreateDto memberCreateDto) {
        return Member.builder()
                .name(memberCreateDto.getName())
                .phoneNumber(memberCreateDto.getPhoneNumber())
                .status(MemberState.ACTIVE)
                .build();
    }

    public static MemberResponseDto.MemberDetailDto toDetailDto(Member member) {
        return MemberResponseDto.MemberDetailDto.builder()
                .id(member.getId())
                .name(member.getName())
                .phoneNumber(member.getPhoneNumber())
                .status(member.getStatus())
                .createdAt(member.getCreatedAt())
                .updatedAt(member.getUpdatedAt())
                .build();
    }
}
