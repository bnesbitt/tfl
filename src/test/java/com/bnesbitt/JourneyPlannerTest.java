package com.bnesbitt;

import static org.junit.jupiter.api.Assertions.*;

import com.bnesbitt.exceptions.InvalidJourney;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

public class JourneyPlannerTest {

    private static Map<Integer, Zone> zones;

    @BeforeAll
    public static void setupZones() {
        zones = Main.makeZones();
    }

    @Test
    public void noZonesAvailable() {
        // Empty zones.
        var availableZones = new Zones(new HashMap<>());
        var planner = new JourneyPlanner(availableZones);
        assertThrows(InvalidJourney.class, () -> planner.create("station1", "station2", Journey.Type.TUBE));
    }

    @Test
    public void startingStationNotFound() {
        var availableZones = new Zones(zones);
        var planner = new JourneyPlanner(availableZones);
        assertThrows(InvalidJourney.class, () -> planner.create("station1", "Hammersmith", Journey.Type.TUBE));
    }

    @Test
    public void terminatingStationNotFound() {
        var availableZones = new Zones(zones);
        var planner = new JourneyPlanner(availableZones);
        assertThrows(InvalidJourney.class, () -> planner.create("Hammersmith", "station2", Journey.Type.TUBE));
    }

    @Test
    public void validJourneyOnTube() throws InvalidJourney {
        var availableZones = new Zones(zones);
        var planner = new JourneyPlanner(availableZones);
        var journey = planner.create("Hammersmith", "Wimbledon", Journey.Type.TUBE);
        assertEquals(Journey.Type.TUBE, journey.type());
        assertEquals("Hammersmith", journey.start());
        assertEquals("Wimbledon", journey.end());
    }
}
