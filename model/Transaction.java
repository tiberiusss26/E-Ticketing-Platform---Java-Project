package model;

import java.util.Date;

public abstract class Transaction {
    private final User user;
    protected final Date transactionDate;
    public final Double amount;

    public Transaction(User user, Date transactionDate, Double amount) {
        this.transactionDate = transactionDate;
        this.amount = amount;
        this.user = user;
    }

    public abstract double getAmount();


}
