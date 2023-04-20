package Model;

import java.util.Date;

public class Ticket implements Comparable<Ticket>{
    private double price;
    private final Date purchaseDate;
    private final Event event;
    private final Location location;
    boolean valid = true;

    public Ticket(double price, Date purchaseDate, Event event, Location location, boolean valid) {
        this.price = price;
        this.purchaseDate = new Date();
        this.event = event;
        this.location = location;
        this.valid = valid;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setValid(boolean valid) {
        this.valid = valid;
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

    public boolean isValid() {
        return valid;
    }

    @Override
    public int compareTo(Ticket t) {
        if (this.price == t.getPrice()) return 0;
        return this.price > t.getPrice() ? 1 : -1;
    }
}
