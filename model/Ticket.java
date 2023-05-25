package model;

import java.util.Date;

public class Ticket implements Comparable<Ticket>{
    private int ticketId;
    private Client client;
    private double price;
    private final Date purchaseDate;
    private final Event event;
    private final Location location;

    public Ticket(int ticketId, Client client, double price, Date purchaseDate, Event event, Location location) {
        this.ticketId = ticketId;
        this.client = client;
        this.price = price;
        this.purchaseDate = purchaseDate;
        this.event = event;
        this.location = location;
    }

    public int getTicketId() {
        return ticketId;
    }

    public void setTicketId(int ticketId) {
        this.ticketId = ticketId;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Date getPurchaseDate() {
        return purchaseDate;
    }

    public Event getEvent() {
        return event;
    }

    public Location getLocation() {
        return location;
    }

    @Override
    public int compareTo(Ticket t) {
        if (this.price == t.getPrice()) return 0;
        return this.price > t.getPrice() ? 1 : -1;
    }
    @Override
    public String toString() {
        return "Ticket{" +
                "ticketId=" + ticketId +
                ", client=" + client +
                ", price=" + price +
                ", purchaseDate=" + purchaseDate +
                ", event=" + event +
                ", location=" + location +
                '}';
    }
}
