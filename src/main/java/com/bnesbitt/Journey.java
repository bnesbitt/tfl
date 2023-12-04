package com.bnesbitt;

import java.util.Set;

public record Journey(Journey.Type type, String start, String end, Set<Zone> startingZones, Set<Zone> endingZones) {

    public enum Type {
        TUBE,
        BUS
    }

}
