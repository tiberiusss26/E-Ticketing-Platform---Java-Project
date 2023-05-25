package model;

public class Purchase {
    public Client client;
    public Ticket ticket;
    public int numberOfTickets;

    public double totalSum;

    public Purchase(Client client, Ticket ticket, int numberOfTickets, double totalSum) {
        this.client = client;
        this.ticket = ticket;
        this.numberOfTickets = numberOfTickets;
        this.totalSum = totalSum;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Ticket getTicket() {
        return ticket;
    }

    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }

    public int getNumberOfTickets() {
        return numberOfTickets;
    }

    public void setNumberOfTickets(int numberOfTickets) {
        this.numberOfTickets = numberOfTickets;
    }

    public double getTotalSum() {
        return totalSum;
    }

    public void setTotalSum(double totalSum) {
        this.totalSum = totalSum;
    }

    @Override
    public String toString() {
        return "Purchase{" +
                "client=" + client +
                ", ticket=" + ticket +
                ", numberOfTickets=" + numberOfTickets +
                ", totalSum=" + totalSum +
                '}';
    }
}
