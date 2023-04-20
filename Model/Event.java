package Model;

import java.util.Date;

public class Event {

    private final String name;
    private final Date date;
    private long availableTickets;
    private final Location location;

    public Event(String name, Date date, Location location) {
        this.name = name;
        this.date = date;
        this.availableTickets = location.getCapacity();
        this.location = location;
    }

    public long getAvailableTickets() {
        return availableTickets;
    }

    public Location getLocation() {
        return location;
    }

    public Date getDate() {
        return date;
    }

    public String getName() {
        return name;
    }

    public void setAvailableTickets(long availableTickets) {
        this.availableTickets = availableTickets;
    }
}
