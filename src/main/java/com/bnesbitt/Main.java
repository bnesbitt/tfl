package com.bnesbitt;

import com.bnesbitt.exceptions.InsufficientFunds;
import com.bnesbitt.exceptions.InvalidFunds;
import com.bnesbitt.exceptions.InvalidJourney;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
    public static Map<Integer, Zone> makeZones() {
        var zone1 = new Zone(1, 250);
        zone1.addStation("Holborn");
        zone1.addStation("Earl's Court");

        var zone2 = new Zone(2, 200);
        zone2.addStation("Earl's Court");
        zone2.addStation("Hammersmith");

        var zone3 = new Zone(3, 200);
        zone3.addStation("Wimbledon");

        Map<Integer, Zone> zones = new HashMap<>();
        zones.put(1, zone1);
        zones.put(2, zone2);
        zones.put(3, zone3);

        return zones;
    }

    public static void main(String[] args) throws InvalidJourney, InsufficientFunds, InvalidFunds {
        var zones = makeZones();
        var availableZones = new Zones(zones);
        var journeyPlanner = new JourneyPlanner(availableZones);

        var barrier = new Barrier();
        var card = new OysterCard(3000);

        List<Journey> journies = Arrays.asList(
                // Stage1: Tube Holborn to Earl's Court
                journeyPlanner.create("Holborn", "Earl's Court", Journey.Type.TUBE),

                // Stage2: Bus to Chelsea
                journeyPlanner.create("328 Bus", "Chelsea", Journey.Type.BUS),

                // Stage 3: Tube Earl’s court to Hammersmith
                journeyPlanner.create("Earl's Court", "Hammersmith", Journey.Type.TUBE)
        );

        System.out.println("\nJourney Planner\n");
        for (var journey : journies) {
            System.out.println("");
            barrier.swipeIn(journey, card);
            barrier.swipeOut(journey, card);
            System.out.printf("Current card balance: £%.2f\n", (card.balanceInPence() / 100.0));
        }

        System.out.printf("\nAt the end of the journey, the card balance is £%.2f\n", (card.balanceInPence() / 100.0));
    }

}