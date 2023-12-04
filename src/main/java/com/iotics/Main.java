package com.iotics;

import com.iotics.exceptions.InsufficientFunds;
import com.iotics.exceptions.InvalidFunds;
import com.iotics.exceptions.InvalidJourney;

import java.util.HashMap;
import java.util.Map;

public class Main {
    private static Map<Integer, Zone> makeZones() {
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

        // Stage1: Tube Holborn to Earl's Court
        var stage1 = journeyPlanner.create("Holborn", "Earl's Court", Journey.Type.TUBE);
        barrier.swipeIn(stage1, card);
        barrier.swipeOut(stage1, card);
        System.out.println("Current card balance: " + (card.balanceInPence() / 100));

        // Stage2: Bus to Chelsea
        System.out.println("");
        var stage2 = journeyPlanner.create("328 Bus", "Chelsea", Journey.Type.BUS);
        barrier.swipeIn(stage2, card);
        barrier.swipeOut(stage2, card);
        System.out.println("Current card balance: " + (card.balanceInPence() / 100));

        // Stage 3: Tube Earl’s court to Hammersmith
        System.out.println("");
        var stage3 = journeyPlanner.create("Earl's Court", "Hammersmith", Journey.Type.TUBE);
        barrier.swipeIn(stage3, card);
        barrier.swipeOut(stage3, card);
        System.out.println("Current card balance: " + (card.balanceInPence() / 100));
    }

}