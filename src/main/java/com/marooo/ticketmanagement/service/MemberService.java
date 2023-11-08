package com.marooo.ticketmanagement.service;

import com.marooo.ticketmanagement.controller.dto.MemberRequestDto;
import com.marooo.ticketmanagement.controller.dto.MemberResponseDto;
import com.marooo.ticketmanagement.converter.MemberConverter;
import com.marooo.ticketmanagement.domain.member.Member;
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

    public List<MemberResponseDto.DetailDto> getAllMembers() {
        return memberRepository.findAll().stream()
                .map(MemberConverter::toDetailDto)
                .toList();
    }

    public MemberResponseDto.DetailDto getMemberById(Long memberId) {
        final Member member = memberRepository.findById(memberId).orElseThrow(() -> new NoSuchElementException("Member not found"));
        return MemberConverter.toDetailDto(member);
    }

    @Transactional(readOnly = false)
    public MemberResponseDto.DetailDto createMember(MemberRequestDto.CreateDto createDto) {
        if (memberRepository.existsByName(createDto.getName()))
            throw new IllegalArgumentException("Member already exists");
        final Member member = memberRepository.save(MemberConverter.toMember(createDto));
        return MemberConverter.toDetailDto(member);
    }

    @Transactional(readOnly = false)
    public void deleteMember(Long memberId) {
        if (!memberRepository.existsById(memberId))
            throw new NoSuchElementException("Member not found");
        memberRepository.deleteById(memberId);
    }
}
