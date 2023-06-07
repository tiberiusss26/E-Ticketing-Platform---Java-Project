package model;

import database.MySqlConnection;
import repositories.ClientRepository;
import repositories.EventRepository;
import repositories.IUserRepository;
import repositories.OrganizerRepository;
import service.UserService;

import java.security.NoSuchAlgorithmException;
import java.util.*;

public class Main {


    public static void main(String[] args) throws NoSuchAlgorithmException {

        // ceva cu sortare -- clientii si organizatorii se afiseaza crescator in functie de creditScore
        // meniu interactiv

        MySqlConnection connection = MySqlConnection.getInstance();
        ClientRepository clientRepository = new ClientRepository(connection);
        OrganizerRepository organizerRepository = new OrganizerRepository(connection);
        EventRepository eventRepository = new EventRepository(connection, organizerRepository);

//        User user1 = new User("tibi","tibii");
        User user2 = new User("andrei", "tibii");
//        clientRepository.addUser(user2);
//        clientRepository.removeUser(user2);

//        System.out.println(clientRepository.getUserByUsername("tibi").get());

        User organizator = new Organizer("Marcel", "p");

//        organizerRepository.addUser(organizator);
//        organizerRepository.removeUser(organizator);
        Date data = new java.sql.Date(2023 - 1900, 10, 12);
        Location constanta = new Location("Constanta", "Romania", 100000);
        Event event = new Event("Untold", data, constanta, (Organizer) organizator);


//        eventRepository.addEvent(event);
//        eventRepository.removeEvent("Neversea");
//        eventRepository.updateEvent("Untold", 20);
//        eventRepository.updateEvent("untold", 20);

//        for (Event even : eventRepository.listEvents())
//            System.out.println(even);

        UserService userService = new UserService(clientRepository, organizerRepository);

//        System.out.println(organizerRepository.getUserByUsername("marcel").get());
//        userService.listAllOrganizers();

        Scanner scanner = new Scanner(System.in);

        boolean read = true;

        System.out.println("Apasati tasta 0 pentru a inchide aplicatia\n" +
                "Apasati tasta 1 pentru a crea un user\n" +
                "Apasati tasta 2 pentru a sterge un user\n" +
                "");

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
                    if(type.equals("client"))
                        System.out.println("Clinet added successfully!");
                    else System.out.println("Organizer added successfully!");
                }
                case "2" -> {
                    System.out.println("Username to be deleted: ");
                    String username = scanner.next();

                    userService.removeUser(username);

                    System.out.println("User delted successfully!");
                }
            }


        }


    }
}
