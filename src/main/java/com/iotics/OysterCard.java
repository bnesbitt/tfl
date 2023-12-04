package com.iotics;

import com.iotics.exceptions.InsufficientFunds;
import com.iotics.exceptions.InvalidFunds;

public class OysterCard {
    private int balance;

    public OysterCard(int fundsInPence) throws InvalidFunds {
        if (fundsInPence < 0) {
            throw new InvalidFunds("Cannot deposit negative funds: " + fundsInPence);
        }
        this.balance = fundsInPence;
    }

    public int balanceInPence() {
        return balance;
    }

    public void credit(int credit) throws InvalidFunds {
        if (credit < 0) {
            throw new InvalidFunds("Cannot credit card with a negative amount: ["
                    + credit + "] try debit instead");
        }

        this.balance += credit;

        System.out.println("Crediting card by: " + credit);
        System.out.println("Remaining balance: " + balance);
    }

    public void debit(int fee) throws InsufficientFunds {
        if (fee > balance) {
            throw new InsufficientFunds("This card only has " + balance
                    + "p and does not have enough funds to be charged " + fee);
        }

        balance -= fee;

        System.out.println("Debiting card by:  " + fee);
        System.out.println("Remaining balance: " + balance);
    }

}
