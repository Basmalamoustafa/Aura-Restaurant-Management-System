package com.restaurant;

public class Reservation {
    private String date;
    private String time;
    private int partySize;
    private String specialRequests;
    private String reservedBy;

    public Reservation(String date, String time, int partySize, String specialRequests, String reservedBy) {
        this.date = date;
        this.time = time;
        this.partySize = partySize;
        this.specialRequests = specialRequests;
        this.reservedBy = reservedBy;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public int getPartySize() {
        return partySize;
    }

    public String getSpecialRequests() {
        return specialRequests;
    }

    public String getReservedBy() {
        return reservedBy;
    }
}