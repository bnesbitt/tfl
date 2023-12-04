package com.bnesbitt;

import static org.junit.jupiter.api.Assertions.*;

import com.bnesbitt.exceptions.InsufficientFunds;
import com.bnesbitt.exceptions.InvalidFunds;
import com.bnesbitt.exceptions.InvalidJourney;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Map;

public class BarrierTest {

    private static Map<Integer, Zone> zones;

    @BeforeAll
    public static void setupZones() {
        zones = Main.makeZones();
    }

    @Test
    public void cardHasNoBalance() throws InvalidJourney {
        // Create a card with no funds.
        var card = new OysterCard();

        var barrier = new Barrier();
        var availableZones = new Zones(zones);
        var planner = new JourneyPlanner(availableZones);

        var journey = planner.create("Holborn", "Earl's Court", Journey.Type.TUBE);
        assertThrows(InsufficientFunds.class, () -> barrier.swipeIn(journey, card));
    }

    @Test
    public void exitWithoutEntry() throws InvalidJourney, InsufficientFunds, InvalidFunds {
        var card = new OysterCard(3000);

        var barrier = new Barrier();
        var availableZones = new Zones(zones);
        var planner = new JourneyPlanner(availableZones);

        var journey = planner.create("Holborn", "Earl's Court", Journey.Type.TUBE);
        barrier.swipeIn(journey, card);
        assertEquals(2700, card.balanceInPence());
    }
}
