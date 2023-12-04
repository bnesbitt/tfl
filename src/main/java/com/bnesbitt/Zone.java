package com.bnesbitt;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Zone implements Comparable<Zone> {
    // The zone id.
    private final int id;

    // The cost of travel within the zone - in pence.
    private final int priceInPence;

    // The stations within the zone.
    private final List<String> stations = new ArrayList<>();

    /**
     * Creates a new travel zone.
     *
     * @param id The zone id.
     *
     * @param priceInPence The cost of travel within the zone.
     */
    public Zone(int id, int priceInPence) {
        this.id = id;
        this.priceInPence = priceInPence;
    }

    public int getId() {
        return id;
    }

    public int getPriceInPence() {
        return priceInPence;
    }

    public boolean addStation(String station) {
        if (!stations.contains(station)) {
            stations.add(station);
            return true;
        } else {
            System.err.printf("Can't add station [%s] to zone [%d], as it has already been added!", station, id);
            return false;
        }
    }

    public boolean hasStation(String station) {
        return stations.contains(station);
    }

    public List<String> getStations() {
        return stations;
    }

    /**
     * Allows us to compare zones by their fare.
     *
     * @param zone the object to be compared.
     */
    @Override
    public int compareTo(Zone zone) {
        if (priceInPence == zone.priceInPence) {
            return 0;
        }

        if (priceInPence < zone.priceInPence) {
            return -1;
        }

        return 1;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Zone zone)) return false;
        return getId() == zone.getId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

}
