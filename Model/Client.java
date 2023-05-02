package Model;

import Exceptions.AvailabilityException;
import Exceptions.BalanceException;
import Exceptions.LoginException;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

public class Client extends User {

    private List<Ticket> Inventory;
    private double creditScore;

    public Client(String username, String password) throws LoginException, NoSuchAlgorithmException {
        super(username, password);
        Inventory = new ArrayList<>();
        this.creditScore = 0;
    }

    public List<Ticket> getInventory() {
        return Inventory;
    }

    public double getCreditScore() {
        return creditScore;
    }

    public void setCreditScore(double creditScore) {
        this.creditScore = creditScore;
    }

    public void addTicket(Ticket ticket, long quantity) throws BalanceException, AvailabilityException {
        if (ticket.getPrice() * quantity > this.creditScore)
            throw new BalanceException("Insufficient funds!");
        if (quantity > ticket.getEvent().getAvailableTickets())
            throw new AvailabilityException("Not enough tickets available!");

        for (int i = 0; i < quantity; i++)
            Inventory.add(ticket);

        ticket.getEvent().setAvailableTickets(ticket.getEvent().getAvailableTickets() - quantity);
        this.creditScore -= quantity * ticket.getPrice();


    }
}
