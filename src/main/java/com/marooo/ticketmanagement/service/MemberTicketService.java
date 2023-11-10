package com.marooo.ticketmanagement.service;

import com.marooo.ticketmanagement.controller.dto.MemberTicketResponseDto;
import com.marooo.ticketmanagement.converter.MemberTicketConverter;
import com.marooo.ticketmanagement.domain.mapping.memberTicket.MemberTicket;
import com.marooo.ticketmanagement.domain.member.Member;
import com.marooo.ticketmanagement.domain.ticket.Ticket;
import com.marooo.ticketmanagement.repository.MemberRepository;
import com.marooo.ticketmanagement.repository.MemberTicketRepository;
import com.marooo.ticketmanagement.repository.TicketRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberTicketService {
    private final MemberRepository memberRepository;
    private final TicketRepository ticketRepository;
    private final MemberTicketRepository memberTicketRepository;

    public List<MemberTicketResponseDto.DetailDto> getMemberTicketsByMemberId(Long memberId) {
        return memberTicketRepository.findByMemberId(memberId).stream()
                .map(MemberTicketConverter::toDetailDto).toList();
    }

    @Transactional(readOnly = false)
    public MemberTicketResponseDto.SummaryDto assignTicketToMember(Long memberId, Long ticketId) {
        final Member member = memberRepository.findById(memberId).orElseThrow(() -> new IllegalArgumentException("Member not found"));
        final Ticket ticket = ticketRepository.findById(ticketId).orElseThrow(() -> new IllegalArgumentException("Ticket not found"));
        final MemberTicket memberTicket = memberTicketRepository.save(MemberTicketConverter.toMemberTicket(member, ticket));
        return MemberTicketConverter.toSummaryDto(memberTicket);
    }

    @Transactional(readOnly = false)
    public void deleteMemberTicket(Long memberId, Long ticketId) {
        if (memberRepository.existsById(memberId))
            throw new IllegalArgumentException("Member not found");
        if (ticketRepository.existsById(ticketId))
            throw new IllegalArgumentException("Ticket not found");
        memberTicketRepository.deleteByMemberIdAndTicketId(memberId, ticketId);
    }
}
