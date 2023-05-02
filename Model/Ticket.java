package Model;

import java.util.Date;

public class Ticket implements Comparable<Ticket>{
    private double price;
    private final Date purchaseDate;
    private final Event event;
    private final Location location;


    public Ticket(double price, Date purchaseDate, Event event, Location location, boolean valid) {
        this.price = price;
        this.purchaseDate = new Date();
        this.event = event;
        this.location = location;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getPrice() {
        return price;
    }

    public Date getData() {
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
                "price=" + price +
                ", purchaseDate=" + purchaseDate +
                ", event=" + event +
                ", location=" + location +
                '}';
    }
}
