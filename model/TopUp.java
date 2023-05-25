package model;

import java.util.Date;

public final class TopUp extends Transaction {

    public TopUp(User user, Date transactionDate, Double amount) {
        super(user, transactionDate, amount);
    }

    @Override
    public double getAmount() {
        return amount;
    }
}
