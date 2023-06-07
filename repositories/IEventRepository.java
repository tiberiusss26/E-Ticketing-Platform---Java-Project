package repositories;

import model.Event;

import java.util.List;
import java.util.Optional;

public interface IEventRepository {

    List<Event> listEvents();
    Optional<Event> getEventByName(String name);
    void addEvent(Event event);
    void removeEvent(String eventName);
    void updateEvent(String eventName, int ticketsBought);
}
