package com.bnesbitt;

import java.util.*;

public class Zones {

    private final Map<Integer, Zone> zones;

    public Zones(Map<Integer, Zone> zones) {
        this.zones = zones;
    }

    public Set<Zone> getZonesForStation(String station) {
        Set<Zone> zonesForStation = new HashSet<>();

        for (Map.Entry<Integer, Zone>  entry : zones.entrySet()) {
            var zone = entry.getValue();
            if (zone.hasStation(station)) {
                zonesForStation.add(zone);
            }
        }

        return zonesForStation;
    }

}
