package model;

import database.MySqlConnection;
import repositories.ClientRepository;
import repositories.EventRepository;
import repositories.IUserRepository;
import repositories.OrganizerRepository;
import service.EventService;
import service.UserService;

import javax.sound.midi.Soundbank;
import java.security.NoSuchAlgorithmException;
import java.util.*;

public class Main {


    public static void main(String[] args) throws NoSuchAlgorithmException {

        // ceva cu sortare -- clientii si organizatorii se afiseaza crescator in functie de creditScore

        MySqlConnection connection = MySqlConnection.getInstance();
        ClientRepository clientRepository = new ClientRepository(connection);
        OrganizerRepository organizerRepository = new OrganizerRepository(connection);
        EventRepository eventRepository = new EventRepository(connection, organizerRepository);
        UserService userService = new UserService(clientRepository, organizerRepository);
        EventService eventService = new EventService(eventRepository, organizerRepository);
        Scanner scanner = new Scanner(System.in);

        boolean read = true;

        System.out.println("Apasati tasta 0 pentru a inchide aplicatia\n" +
                "Apasati tasta 1 pentru a crea un user\n" +
                "Apasati tasta 2 pentru a sterge un user\n" +
                "Apasati tasta 3 pentru a crea un eveniment\n" +
                "Apasati tasta 4 pentru a modifica un eveniment\n" +
                "Apasati tasta 5 pentru a afisa toate evenimentele\n" +
                "Apasati tasta 6 pentru a genera un raport\n");

        while (read) {
            String comanda = scanner.nextLine();

            switch (comanda) {
                case "0" -> read = false;
                case "1" -> {
                    System.out.println("Username: ");
                    String username = scanner.next();
                    System.out.println("Password");
                    String password = scanner.next();
                    System.out.println("Type: ");
                    String type = scanner.next();

                    userService.registerUser(username, password, type);
                    if (type.equals("client"))
                        System.out.println("Clinet added successfully!");
                    else System.out.println("Organizer added successfully!");
                }
                case "2" -> {
                    System.out.println("Username to be deleted: ");
                    String username = scanner.next();

                    userService.removeUser(username);

                    System.out.println("User delted successfully!");
                }
                case "3" -> {

                    System.out.println("Event name: ");
                    String eventName = scanner.next();
                    System.out.println("Year: ");
                    int year = scanner.nextInt();
                    System.out.println("Month: ");
                    int month = scanner.nextInt();
                    System.out.println("Day: ");
                    int day = scanner.nextInt();
                    System.out.println("Location name: ");
                    String locationName = scanner.next();
                    System.out.println("Country: ");
                    String country = scanner.next();
                    System.out.println("Capacity: ");
                    long capacity = scanner.nextLong();
                    System.out.println("Organizer Username: ");
                    String organizerUsername = scanner.next();

                    eventService.addEvent(eventName, year, month, day, locationName, country, capacity, organizerUsername);

                    System.out.println("Event added successfully!");
                }
                case "4" -> {
                    System.out.println("Event name: ");
                    String eventName = scanner.next();
                    System.out.println("Tickets sold: ");
                    int ticketsBought = scanner.nextInt();

                    eventService.updateEvent(eventName, ticketsBought);
                    System.out.println("Event updated successfully!");
                }
                case "5" -> {
                    eventService.listEvents();

                    System.out.println("Events listed successfully!");
                }
                case "6" -> {
                    userService.generateReport();

                    System.out.println("Report created successfully!");
                }
                default -> System.out.println("Command does not exist!");

            }


        }


    }
}
