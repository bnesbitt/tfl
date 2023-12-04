package com.iotics;

import com.iotics.exceptions.InvalidJourney;

public class JourneyPlanner {

    private final Zones availableZones;

    public JourneyPlanner(Zones availableZones) {
        this.availableZones = availableZones;
    }

    public Journey create(String start, String end, Journey.Type type) throws InvalidJourney {

        if (type == Journey.Type.TUBE) {
            var startingZones = availableZones.getZonesForStation(start);
            if (null == startingZones || startingZones.isEmpty()) {
                throw new InvalidJourney("Invalid starting point [" + start + "] - location cannot be located in any zone!");
            }

            var endingZones = availableZones.getZonesForStation(end);
            if (null == endingZones || endingZones.isEmpty()) {
                throw new InvalidJourney("Invalid destination [" + end + "] - location cannot be located in any zone!");
            }

            return new Journey(type, start, end, startingZones, endingZones);
        }

        return new Journey(type, start, end, null, null);
    }

}
