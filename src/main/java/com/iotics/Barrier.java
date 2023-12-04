package com.iotics;

import com.iotics.exceptions.InsufficientFunds;
import com.iotics.exceptions.InvalidFunds;

import java.util.HashSet;

public class Barrier {

    private final int BUS_FARE = 180;
    private final int INCLUDES_ZONE1 = 300;
    private final int EXCLUDES_ZONE1 = 225;

    // Tracks the number of passengers that have swiped in and are in transit.
    private int passengersInTransit = 0;

    /**
     * Charge the commuter with the maximum fee as they enter.
     *
     * @param journey The commuters journey with starting and end points.
     *
     * @param card The Oyster card to charge.
     *
     * @throws InsufficientFunds If the commuter does not have sufficient funds on their card.
     */
    void swipeIn(Journey journey, OysterCard card) throws InsufficientFunds {
        System.out.println("Journey Starting at " + journey.start());
        System.out.println("Current funds available: " + (card.balanceInPence()/100.0));

        if (journey.type() == Journey.Type.BUS) {
            System.out.println("Charging bus fare at " + BUS_FARE);
            card.debit(BUS_FARE);
            return;
        }

        int price = getMaxFee(journey);
        card.debit(price);

        ++passengersInTransit;
    }

    /**
     * When the commuter passes out of the barrier at the exit station, the fare is calculated and the maximum
     * fare transaction removed and replaced with the real transaction (in this way, if the user
     * doesn’t swipe out, they are charged the maximum fare).
     *
     * @param journey The commuters journey with starting and end points.
     *
     * @param card The Oyster card to charge.
     *
     * @throws InvalidFunds If we attempt to credit the card with a negative amount.
     *                      That can only be done via debit.
     */
    void swipeOut(Journey journey, OysterCard card) throws InvalidFunds {
        System.out.println("Terminating journey at " + journey.end());

        if (journey.type() == Journey.Type.BUS) {
            return;
        }

        int maximumPrice = getMaxFee(journey);
        int minimumPrice = getMinFee(journey);

        System.out.println("Reduced exit fee is: " + minimumPrice);

        if (maximumPrice > minimumPrice) {
            int refund = maximumPrice - minimumPrice;
            System.out.println("Refunding card with: " + refund);
            card.credit(refund);
        }

        --passengersInTransit;
    }

    public int getNumberOfPassengersInTransit() {
        return passengersInTransit;
    }

    private int getMaxFee(Journey journey) {
        var price = 0;

        var startingZones = journey.startingZones();
        var endingZones = journey.endingZones();

        // Merge the zones into one set, so we can filter out duplicates and be left with up to two distinct zones.
        var results = new HashSet<>(startingZones);
        results.addAll(endingZones);

        // Are we in just one zone?
        if (results.size() == 1) {
            var currentZone = results.iterator().next();
            price = currentZone.getPriceInPence();
            System.out.println("Charging " + price + " for travel in zone " + currentZone.getId());
            return price;
        }

        // More than one zone.

        // Are we within zone1?
        boolean includesZone1 = false;
        for (Zone z : startingZones) {
            if (z.getId() == 1) {
                includesZone1 = true;
                break;
            }
        }

        if (includesZone1) {
            System.out.println("Journey is two zones including zone1, charging " + INCLUDES_ZONE1);
            price = INCLUDES_ZONE1;
        } else {
            System.out.println("Journey is two zones excluding zone1, charging " + EXCLUDES_ZONE1);
            price = EXCLUDES_ZONE1;
        }

        return price;
    }

    private int getMinFee(Journey journey) {
        var startingZones = journey.startingZones();
        var endingZones = journey.endingZones();

        // Merge the zones and keep the common entries.
        var results = new HashSet<>(endingZones);
        results.retainAll(startingZones);

        int minimumPrice = 0;
        for (Zone z : results) {
            minimumPrice += z.getPriceInPence();
            System.out.println("Zone used for minimum exit price: " + z.getId());
        }

        return minimumPrice;
    }

}
