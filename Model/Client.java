package Model;

import Exceptions.AvailabilityException;
import Exceptions.BalanceException;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

public class Client extends User{

    private List<Ticket> Inventory;
    private double creditScore;

    public Client(String username, String password) throws NoSuchAlgorithmException {
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

    public void addTicket(Ticket ticket, long cantity) throws BalanceException, AvailabilityException {
        if(ticket.getPrice() * cantity > this.creditScore)
            throw new BalanceException("Insufficient funds!");
        if(cantity > ticket.getEvent().getAvailableTickets())
            throw new AvailabilityException("Not enough tickets available!");

        Inventory.add(ticket);
        ticket.getEvent().setAvailableTickets(ticket.getEvent().getAvailableTickets() - cantity);


    }
}
