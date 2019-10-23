package seedu.address.logic.internal.gmaps;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.net.ConnectException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Hashtable;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.TimeBookInvalidState;
import seedu.address.model.gmaps.Location;
import seedu.address.model.gmaps.LocationGraph;

class ClosestLocationTest {
    private ClosestLocation closestLocation;
    @BeforeEach
    void init() throws ConnectException, TimeBookInvalidState {
        ProcessVenues processVenues;
        processVenues = new ProcessVenues().process();
        ArrayList<Location> locations = processVenues.getLocations();
        ArrayList<String> validLocationList = processVenues.getValidLocationList();
        LocationGraph locationGraph = new LocationGraph(locations, validLocationList);
        new ProcessLocationGraph(locationGraph).process();
        closestLocation = new ClosestLocation(locationGraph);
    }

    @Test
    void closestLocationData() {
        ArrayList<String> locationNameList = new ArrayList<>(Arrays.asList("LT17", "LT17", "LT17"));
        Hashtable<String, Object> result = closestLocation.closestLocationData(locationNameList);
        assertEquals((String) result.get(ClosestLocationSyntax.FIRST_CLOSEST), "LT17");
        assertEquals((long) result.get(ClosestLocationSyntax.FIRST_CLOSEST_AVG_TIME), (long) 0);

    }

    @Test
    void closestLocation() {
        ArrayList<String> locationNameList = new ArrayList<>(Arrays.asList("LT17", "LT17", "LT17"));
        String result = closestLocation.closestLocationDataString(locationNameList);
        String expectedResult = "First closest location: LT17 | Average travelling time 0\n"
                + "Second closest location: LT19 | Average travelling time 4\n"
                + "Third closest location: AS4 | Average travelling time 70\n";
        assertEquals(expectedResult, result);
    }
}