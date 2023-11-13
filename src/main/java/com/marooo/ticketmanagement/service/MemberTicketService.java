package com.marooo.ticketmanagement.service;

import com.marooo.ticketmanagement.controller.dto.MemberTicketResponseDto;
import com.marooo.ticketmanagement.converter.MemberTicketConverter;
import com.marooo.ticketmanagement.domain.mapping.memberTicket.MemberTicket;
import com.marooo.ticketmanagement.domain.member.Member;
import com.marooo.ticketmanagement.domain.ticket.Ticket;
import com.marooo.ticketmanagement.exception.ErrorMessage;
import com.marooo.ticketmanagement.repository.MemberRepository;
import com.marooo.ticketmanagement.repository.MemberTicketRepository;
import com.marooo.ticketmanagement.repository.TicketRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberTicketService {
    private final MemberRepository memberRepository;
    private final TicketRepository ticketRepository;
    private final MemberTicketRepository memberTicketRepository;

    public List<MemberTicketResponseDto.MemberTicketDetailDto> getMemberTicketsByMemberId(Long memberId) {
        if (!memberRepository.existsById(memberId))
            throw new NoSuchElementException(ErrorMessage.MEMBER_NOT_FOUND.getMessage());
        return memberTicketRepository.findByMemberId(memberId).stream()
                .map(MemberTicketConverter::toDetailDto).toList();
    }

    @Transactional(readOnly = false)
    public MemberTicketResponseDto.MemberTicketSummaryDto assignTicketToMember(Long memberId, Long ticketId) {
        final Member member = memberRepository.findById(memberId).orElseThrow(() -> new IllegalArgumentException(ErrorMessage.MEMBER_NOT_FOUND.getMessage()));
        final Ticket ticket = ticketRepository.findById(ticketId).orElseThrow(() -> new IllegalArgumentException(ErrorMessage.TICKET_NOT_FOUND.getMessage()));
        final MemberTicket memberTicket = memberTicketRepository.save(MemberTicketConverter.toMemberTicket(member, ticket));
        return MemberTicketConverter.toSummaryDto(memberTicket);
    }

    @Transactional(readOnly = false)
    public void deleteMemberTicket(Long memberId, Long ticketId) {
        if (memberRepository.existsById(memberId))
            throw new NoSuchElementException(ErrorMessage.MEMBER_NOT_FOUND.getMessage());
        if (ticketRepository.existsById(ticketId))
            throw new NoSuchElementException(ErrorMessage.TICKET_NOT_FOUND.getMessage());
        memberTicketRepository.deleteByMemberIdAndTicketId(memberId, ticketId);
    }
}
