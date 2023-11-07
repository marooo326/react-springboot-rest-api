package com.marooo.ticketmanagement.domain.ticket;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class SubscriptionTicket extends Ticket {
    @Column
    private Integer validDays;
}
