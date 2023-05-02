package Model;

import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class Main {
    public static void main(String[] args) throws NoSuchAlgorithmException {
        Location locatie = new Location("Romania", "ro", 3000);
        Organizer organizator = new Organizer("t", "parol");
        Date data = new Date(2023, 12, 3);
        Event event = new Event("Bal", data, locatie, organizator);
        Client client1 = new Client("tibi", "parola");
        Client client2 = new Client("tibi", "parola");

        client1.setCreditScore(200);
        Ticket ticket = new Ticket(100, new Date(), event, locatie, true);
        System.out.println(event.getAvailableTickets());

        client1.addTicket(ticket, 2);
//        client1.addTicket(ticket,1);
        System.out.println(event.getAvailableTickets());

        for (Ticket tick : client1.getInventory()) {
            System.out.println(tick);
        }

        Set<Integer> set = new HashSet<>();

        set.add(10);
        set.add(20);
        set.add(30);


        System.out.println(set);


        System.out.println(client1.getClass().toString().equals("class Model.Client"));


    }
}
