package com.bnesbitt;

import com.bnesbitt.exceptions.InsufficientFunds;
import com.bnesbitt.exceptions.InvalidFunds;

public class OysterCard {
    private int balance;
    private boolean hasSwipedIn = false;

    /**
     * Creates a new card with a starting balance.
     *
     * @param fundsInPence The amount to fund the card with.
     *
     * @throws InvalidFunds If the funds are negative.
     */
    public OysterCard(int fundsInPence) throws InvalidFunds {
        if (fundsInPence < 0) {
            throw new InvalidFunds("Cannot deposit negative funds: " + fundsInPence);
        }
        this.balance = fundsInPence;
    }

    /**
     * Creates a new card with no balance.
     */
    public OysterCard() {
        this.balance = 0;
    }

    public int balanceInPence() {
        return balance;
    }

    /**
     * Adds credit to the card.
     * @param credit The amount to credit the card.
     *
     * @throws InvalidFunds If the credit amount is negative. That should be done using a debit.
     */
    public void credit(int credit) throws InvalidFunds {
        if (credit < 0) {
            throw new InvalidFunds("Cannot credit card with a negative amount: ["
                    + credit + "] try debit instead");
        }

        this.balance += credit;

        System.out.println("\tCrediting card by: " + credit);
        System.out.println("\tRemaining balance: " + balance);
    }

    /**
     * Debits the cards.
     *
     * @param fee The amount of money to take from the users card account.
     *
     * @throws InsufficientFunds If there's insufficient funds.
     */
    public void debit(int fee) throws InsufficientFunds {
        if (fee > balance) {
            throw new InsufficientFunds("This card only has " + balance
                    + "p and does not have enough funds to be charged " + fee);
        }

        balance -= fee;

        System.out.println("\tDebiting card by:  " + fee);
        System.out.println("\tRemaining balance: " + balance);
    }

    public void swipeIn() {
        hasSwipedIn = true;
    }

    public void swipeOut() {
        hasSwipedIn = false;
    }

    public boolean hasSwipedIn() {
        return hasSwipedIn;
    }

}
