package com.marooo.ticketmanagement.domain.ticketTemplate;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class MultiUseTicketTemplate extends TicketTemplate {
    @Column
    private Integer useLimit;
}
