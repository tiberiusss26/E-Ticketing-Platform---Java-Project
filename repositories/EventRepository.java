package repositories;

import database.MySqlConnection;
import model.Event;
import model.Organizer;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public class EventRepository implements IEventRepository {

    private final MySqlConnection connection;

    private final OrganizerRepository organizerRepository;

    private static final String listEvents =
            "SELECT name, date, availableTickets, locationName, organizerName FROM event";

    private static final String insertEvent = "INSERT into event values(?,?,?,?,?)";

    private static final String deleteEvent = "DELETE FROM event where name = ? ";

    private static final String eventByName = "SELECT * FROM event WHERE name = ?";

    private static final String updateEvent = "UPDATE event set availableTickets = ? where name = ?";

    public EventRepository(MySqlConnection connection, OrganizerRepository organizerRepository) {
        this.connection = connection;
        this.organizerRepository = organizerRepository;
    }

    @Override
    public Optional<Event> getEventByName(String eventName) {
        try {
            PreparedStatement preparedStatement = connection.getConnection().prepareStatement(eventByName);
            preparedStatement.setString(1, eventName);

            ResultSet rs = preparedStatement.executeQuery();

            if(!rs.next()){
                return Optional.empty();
            }

            String name = rs.getString("name");
            Date date = rs.getObject("date", Date.class);
            int availableTickets = rs.getInt("availableTickets");
            String locationName = rs.getString("locationName");
            String organizerName = rs.getString("organizerName");

            return Optional.of(new Event(name, date, availableTickets, locationName, organizerRepository.getUserByUsername(organizerName)));


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Event> listEvents() {
        try {
            Statement statement = connection.getConnection().createStatement();
            ResultSet rs = statement.executeQuery(listEvents);

            List<Event> events = new ArrayList<>();

            while (rs.next()) {
                String name = rs.getString("name");
                Date dateTime = rs.getObject("date", Date.class);
                int availableTickets = rs.getInt("availableTickets");
                String locationName = rs.getString("locationName");
                String organizerName = rs.getString("organizerName");

                Event event = new Event(name, dateTime, availableTickets, locationName, organizerRepository.getUserByUsername(organizerName));
                events.add(event);
            }

            return events;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void addEvent(Event event) {
        try {
            PreparedStatement preparedStatement = connection.getConnection().prepareStatement(insertEvent);

            preparedStatement.setString(1, event.getName());
            preparedStatement.setDate(2, (java.sql.Date) event.getDate());
            preparedStatement.setLong(3, event.getAvailableTickets());
            preparedStatement.setString(4, event.getLocation().getName());
            preparedStatement.setString(5, event.getOrganizer().getName());

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void removeEvent(String eventName) {
        try {
            PreparedStatement preparedStatement = connection.getConnection().prepareStatement(deleteEvent);
            preparedStatement.setString(1, eventName);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void updateEvent(String eventName, int ticketsBought) {
        try {
            Event event1 = getEventByName(eventName).get();
            PreparedStatement preparedStatement = connection.getConnection().prepareStatement(updateEvent);
            preparedStatement.setLong(1, event1.getAvailableTickets() - ticketsBought);
            preparedStatement.setString(2, eventName);

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
