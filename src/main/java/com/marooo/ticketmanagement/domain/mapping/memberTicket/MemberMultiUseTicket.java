package com.marooo.ticketmanagement.domain.mapping.memberTicket;

import com.marooo.ticketmanagement.exception.ErrorMessage;
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
public class MemberMultiUseTicket extends MemberTicket {
    @Column(nullable = false)
    private Integer useCount;

    @Column(nullable = false)
    private Integer leftCount;

    @Override
    public void updateTicketState() {
        if (leftCount == 0) {
            state = MemberTicketState.EXPIRED;
        }
    }

    @Override
    public void useTicket() {
        if (leftCount <= 0)
            throw new IllegalStateException(ErrorMessage.MEMBER_TICKET_NOT_ENOUGH.getMessage());
        leftCount--;
        useCount++;
        updateTicketState();
    }
}
