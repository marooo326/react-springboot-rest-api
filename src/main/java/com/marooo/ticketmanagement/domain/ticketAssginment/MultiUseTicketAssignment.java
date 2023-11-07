package com.marooo.ticketmanagement.domain.ticketAssginment;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class MultiUseTicketAssignment extends TicketAssignment {
    @Column
    private Integer useCount;
}
