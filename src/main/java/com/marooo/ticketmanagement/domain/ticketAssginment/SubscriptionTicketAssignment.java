package com.marooo.ticketmanagement.domain.ticketAssginment;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.time.LocalDate;

@Entity
public class SubscriptionTicketAssignment extends TicketAssignment {
    @Column
    private LocalDate startDate;
}
