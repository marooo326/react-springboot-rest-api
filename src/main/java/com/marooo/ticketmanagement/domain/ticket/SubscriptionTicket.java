package com.marooo.ticketmanagement.domain.ticket;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
@Getter
@SuperBuilder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class SubscriptionTicket extends Ticket {
    @Column
    private Integer validDays;

    @Override
    public TicketType getTicketType() {
        return TicketType.SUBSCRIPTION;
    }
}
