package com.marooo.ticketmanagement.repository;

import com.marooo.ticketmanagement.domain.ticketTemplate.TicketTemplate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TicketTemplateRepository extends JpaRepository<TicketTemplate, Long> {
}
