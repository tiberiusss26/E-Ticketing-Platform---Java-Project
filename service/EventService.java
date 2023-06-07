package service;

import model.Event;
import model.Location;
import model.Organizer;
import model.User;
import repositories.EventRepository;
import repositories.OrganizerRepository;

import java.util.Date;
import java.util.List;

public class EventService {

    private EventRepository eventRepository;
    private OrganizerRepository organizerRepository;

    private final List<Event> events;

    public EventService(EventRepository eventRepository, OrganizerRepository organizerRepository) {
        this.eventRepository = eventRepository;
        this.organizerRepository = organizerRepository;
        events = eventRepository.listEvents();
    }

    public void addEvent(String eventName, int year, int month, int day, String locationName, String country, long capacity, String organizerUsername){
        User organizator = organizerRepository.getUserByUsername(organizerUsername).get();

        Date data = new java.sql.Date(year - 1900, month, day);
        Location location = new Location(locationName, country, capacity);
        Event event = new Event(eventName, data, location, (Organizer) organizator);

        eventRepository.addEvent(event);
    }

    public void updateEvent(String eventName, int ticketsBought){
        eventRepository.updateEvent(eventName, ticketsBought);
    }

    public void listEvents(){
        for(Event event : eventRepository.listEvents())
            System.out.println(event);
    }
}
