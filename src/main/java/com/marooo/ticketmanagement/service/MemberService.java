package com.marooo.ticketmanagement.service;

import com.marooo.ticketmanagement.controller.dto.MemberRequestDto;
import com.marooo.ticketmanagement.controller.dto.MemberResponseDto;
import com.marooo.ticketmanagement.converter.MemberConverter;
import com.marooo.ticketmanagement.domain.member.Member;
import com.marooo.ticketmanagement.exception.ErrorMessage;
import com.marooo.ticketmanagement.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {

    private final MemberRepository memberRepository;

    public List<MemberResponseDto.MemberDetailDto> getAllMembers() {
        return memberRepository.findAll().stream()
                .map(MemberConverter::toDetailDto)
                .toList();
    }

    public MemberResponseDto.MemberDetailDto getMemberById(Long memberId) {
        final Member member = memberRepository.findById(memberId).orElseThrow(() -> new NoSuchElementException(ErrorMessage.MEMBER_NOT_FOUND.getMessage()));
        return MemberConverter.toDetailDto(member);
    }

    @Transactional(readOnly = false)
    public MemberResponseDto.MemberDetailDto createMember(MemberRequestDto.MemberCreateDto memberCreateDto) {
        if (memberRepository.existsByPhoneNumber(memberCreateDto.getPhoneNumber()))
            throw new IllegalArgumentException(ErrorMessage.MEMBER_ALREADY_EXIST.getMessage());
        final Member member = memberRepository.save(MemberConverter.toMember(memberCreateDto));
        return MemberConverter.toDetailDto(member);
    }

    @Transactional(readOnly = false)
    public void deleteMember(Long memberId) {
        if (!memberRepository.existsById(memberId))
            throw new NoSuchElementException(ErrorMessage.MEMBER_NOT_FOUND.getMessage());
        memberRepository.deleteById(memberId);
    }
}
