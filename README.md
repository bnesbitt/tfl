```
     _____           _              _____               _ 
    |  _  |         | |            /  __ \             | |
    | | | |_   _ ___| |_ ___ _ __  | /  \/ __ _ _ __ __| |
    | | | | | | / __| __/ _ \ '__| | |    / _` | '__/ _` |
    \ \_/ / |_| \__ \ ||  __/ |    | \__/\ (_| | | | (_| |
    \___/ \__, |___/\__\___|_|     \____/\__,_|_|  \__,_|
            __/ |                                         
            |___/                                          
```

# Oyster Card

**Requires JDK17**

Operation:
- When the user passes through the inward barrier at the station, their oyster card is charged
the maximum fare.
- When they pass out of the barrier at the exit station, the fare is calculated and the maximum
fare transaction removed and replaced with the real transaction (in this way, if the user
doesn’t swipe out, they are charged the maximum fare).
- All bus journeys are charged at the same price.
- The system should favour the customer where more than one fare is possible for a given
journey. E.g. Holburn to Earl’s Court is charged at £2.50.

## Stations and zones:

| STATION       | ZONE  |
|---------------|-------|
| Holborn       | 1     |
| Earl’s Court  | 1, 2  |
| Wimbledon     | 3     | 
| Hammersmith   | 2     |


## Build:
Ensure that java 17 is installed, and then:
```
./mvnw clean install
```

## Run:
```
java -jar target/tfl-1.0-jar-with-dependencies.jar
```
