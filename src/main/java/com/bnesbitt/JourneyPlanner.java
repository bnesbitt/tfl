package com.bnesbitt;

import com.bnesbitt.exceptions.InvalidJourney;

public class JourneyPlanner {

    private final Zones availableZones;

    /**
     * Loads the journey planner with all available travel zones.
     *
     * @param availableZones All the available travel zones.
     */
    public JourneyPlanner(Zones availableZones) {
        this.availableZones = availableZones;
    }

    /**
     * Creates a single journey based on the starting and ending location, and the mode
     * of transport - bus or tube.
     *
     * @param start The name of the starting station.
     *
     * @param end The name of the terminating station.
     *
     * @param type The mode of transport.
     *
     * @return A journey object.
     *
     * @throws InvalidJourney If the starting and/or ending stations cannot be found in
     *                        any of the available zones.
     */
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
