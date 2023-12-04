package com.iotics;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class ZoneTest {

    @Test
    public void createZone() {
        var zone = new Zone(1, 123);
        assertEquals(1, zone.getId());
        assertEquals(123, zone.getPriceInPence());
    }
}
