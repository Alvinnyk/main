package seedu.address.logic.internal.gmaps;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.net.ConnectException;
import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.TimeBookInvalidState;
import seedu.address.model.gmaps.Location;

class ProcessVenuesTest {
    private ProcessVenues processVenues;
    @BeforeEach
    void init() {
        processVenues = new ProcessVenues();
    }

    @Test
    void getLocations() throws ConnectException, TimeBookInvalidState {
        assertThrows(TimeBookInvalidState.class, () -> processVenues.getLocations());
        ProcessVenues newProcessVenues = processVenues.process();
        Location lt17 = new Location("LT17");
        lt17.setValidLocation("NUS_LT17");
        assertTrue(newProcessVenues.getLocations().contains(lt17));
    }

    @Test
    void process() throws ConnectException, TimeBookInvalidState {
        ProcessVenues newProcessVenues = processVenues.process();
        assertNotNull(newProcessVenues.getLocations());
        assertNotNull(newProcessVenues.getValidLocationList());
    }

    @Test
    void getValidLocationList() throws ConnectException {
        assertEquals(processVenues.getValidLocationList(), new ArrayList<>());
        ProcessVenues newProcessVenues = processVenues.process();
        assertTrue(newProcessVenues.getValidLocationList().contains("NUS_LT17"));
    }
}