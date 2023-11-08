package com.marooo.ticketmanagement.repository;

import com.marooo.ticketmanagement.domain.memberTicket.MemberTicket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberTicketRepository extends JpaRepository<MemberTicket, Long> {
}
