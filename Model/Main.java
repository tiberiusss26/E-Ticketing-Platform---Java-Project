package Model;

import java.security.NoSuchAlgorithmException;
import java.util.Date;

public class Main {
    public static void main(String[] args) throws NoSuchAlgorithmException {
        Location locatie = new Location("Romania","ro",3000);

        Date data = new Date(2023,12,3);
        Event event = new Event("Bal", data,locatie);
        Client client1 = new Client("tibi","parola");
        client1.setCreditScore(200);
        Ticket ticket = new Ticket(200,new Date(),event,locatie,true);
        System.out.println(event.getAvailableTickets());
        client1.addTicket(ticket,2);
        System.out.println(event.getAvailableTickets());

    }
}
