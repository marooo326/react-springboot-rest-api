package com.marooo.ticketmanagement.repository;

import com.marooo.ticketmanagement.domain.mapping.memberTicket.MemberTicket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MemberTicketRepository extends JpaRepository<MemberTicket, Long> {
    List<MemberTicket> findByMemberId(Long memberId);

    void deleteByMemberIdAndTicketId(Long memberId, Long ticketId);
}
