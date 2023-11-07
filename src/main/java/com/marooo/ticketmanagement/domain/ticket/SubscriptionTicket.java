package com.marooo.ticketmanagement.domain.ticket;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.time.LocalDate;

@Entity
public class SubscriptionTicket extends Ticket {
    @Column
    private LocalDate startDate;
}
