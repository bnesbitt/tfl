package com.bnesbitt;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class ZoneTest {

    @Test
    public void createZone() {
        var zone = new Zone(1, 123);
        assertEquals(1, zone.getId());
        assertEquals(123, zone.getPriceInPence());
    }

    @Test
    public void stationNotFound() {
        var zone = new Zone(1, 123);
        assertFalse(zone.hasStation("Euston"));
    }

    @Test
    public void hasStation() {
        var zone = new Zone(1, 123);
        assertTrue(zone.addStation("Paddington"));
        assertTrue(zone.addStation("Liverpool"));
        assertTrue(zone.hasStation("Paddington"));

        var stations = zone.getStations();
        assertEquals(2, stations.size());
    }

    @Test
    public void alreadyHasStation() {
        var zone = new Zone(1, 123);
        assertTrue(zone.addStation("Paddington"));
        assertTrue(zone.addStation("Liverpool"));

        assertFalse(zone.addStation("Liverpool"));
    }

    @Test
    public void stationsAreSame() {
        var z1 = new Zone(1, 123);
        var z2 = new Zone(1, 123);
        assertTrue(z1.equals(z2));
    }

    @Test
    public void stationsAreNotSame() {
        var z1 = new Zone(1, 123);
        var z2 = new Zone(2, 123);
        assertFalse(z1.equals(z2));
    }
}
