package model;

import java.util.Date;

public class Event {
    private final String name;
    private final Date date;
    private long availableTickets;
    private final Location location;
    private final Organizer organizer;

    public Event(String name, Date date, Location location, Organizer organizer) {

        this.name = name;
        this.date = date;
        this.availableTickets = location.getCapacity();
        this.location = location;
        this.organizer = organizer;

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

    public Organizer getOrganizer() {
        return organizer;
    }
    public void setAvailableTickets(long availableTickets) {
        this.availableTickets = availableTickets;
    }

    @Override
    public String toString() {
        return "Event{" +
                "name='" + name + '\'' +
                ", date=" + date +
                ", availableTickets=" + availableTickets +
                ", location=" + location +
                '}';
    }
}
