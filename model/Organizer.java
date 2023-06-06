package model;

import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.Set;

public class Organizer extends User{

    private String name;
    private String companyName;
    private double balance;

    private Set<Event> events;


    public Organizer(String username, String password, Date creationDate, String name, String companyName, double balance) throws NoSuchAlgorithmException {
        super(username, password, (java.sql.Date) creationDate);
        this.name = name;
        this.companyName = companyName;
        this.balance = balance;
    }

    public Organizer(String username, String password) throws NoSuchAlgorithmException {
        super(username, password);
        balance = 0;
    }

    public double getBalance() {
        return balance;
    }

    public String getName() {
        return name;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public void validateWithdrawl(){
        Date currentDate = new Date();

    }

    @Override
    public String toString() {
        return "Organizer{" +
                "name='" + name + '\'' +
                ", companyName='" + companyName + '\'' +
                ", balance=" + balance +
                '}';
    }
}
