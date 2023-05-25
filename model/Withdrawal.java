package model;

import java.util.Date;

public final class Withdrawal extends Transaction {

    public Withdrawal(User user, Date transactionDate, Double amount) {
        super(user, transactionDate, amount);
    }

    @Override
    public double getAmount() {
        return -amount;
    }
}
