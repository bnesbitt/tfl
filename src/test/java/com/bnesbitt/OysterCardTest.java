package com.bnesbitt;

import static org.junit.jupiter.api.Assertions.*;

import com.bnesbitt.exceptions.InsufficientFunds;
import com.bnesbitt.exceptions.InvalidFunds;
import org.junit.jupiter.api.Test;

public class OysterCardTest {

    @Test
    public void negativeFunds() {
        assertThrows(InvalidFunds.class, () -> {
            new OysterCard(-1);
        });
    }

    @Test
    public void createCardWithNoFunds() throws InvalidFunds {
        var card  = new OysterCard(0);
        assertEquals(0, card.balanceInPence());
    }

    @Test
    public void negativeCredit() throws InvalidFunds {
        var card  = new OysterCard(1000);
        assertThrows(InvalidFunds.class, () -> {
           card.credit(-1);
        });
    }

    @Test
    public void addCredit() throws InvalidFunds {
        var card  = new OysterCard(1000);
        card.credit(123);
        assertEquals(1123, card.balanceInPence());
    }

    @Test
    public void debitWithInsufficientFunds() throws InvalidFunds {
        var card  = new OysterCard(1000);
        assertThrows(InsufficientFunds.class, () -> {
           card.debit(10000000);
        });
    }

    @Test
    public void debit() throws InvalidFunds, InsufficientFunds {
        var card = new OysterCard(123);
        card.debit(23);
        assertEquals(100, card.balanceInPence());
    }
}
