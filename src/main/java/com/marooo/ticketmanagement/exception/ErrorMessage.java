package com.marooo.ticketmanagement.exception;

public enum ErrorMessage {
    // Member
    MEMBER_NOT_FOUND("Member not found"),
    MEMBER_ALREADY_EXIST("Member already exist"),
    MEMBER_NOT_ACTIVE("Member is not active"),
    // Ticket
    TICKET_NOT_FOUND("Ticket not found"),
    TICKET_ALREADY_EXIST("Ticket already exist"),
    TICKET_NOT_ACTIVE("Ticket is not active"),
    // MemberTicket
    MEMBER_TICKET_NOT_FOUND("MemberTicket not found"),
    MEMBER_TICKET_ALREADY_EXIST("MemberTicket already exist"),
    MEMBER_TICKET_NOT_ACTIVE("MemberTicket is not active"),
    MEMBER_TICKET_EXPIRED("MemberTicket is expired"),
    MEMBER_TICKET_NOT_ENOUGH("MemberTicket is not enough"),
    MEMBER_TICKET_NOT_AVAILABLE("MemberTicket is not available"),
    // MemberTicket state
    MEMBER_TICKET_STATE_NOT_FOUND("MemberTicketState not found"),
    MEMBER_TICKET_STATE_ALREADY_EXIST("MemberTicketState already exist"),
    MEMBER_TICKET_STATE_NOT_ACTIVE("MemberTicketState is not active"),
    // Ticket
    STORE_NOT_FOUND("Store not found"),
    STORE_ALREADY_EXIST("Store already exist");
    private final String message;

    ErrorMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
