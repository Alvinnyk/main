package seedu.address.model;

import seedu.address.model.display.schedulewindow.ScheduleWindowDisplay;
import seedu.address.model.display.sidepanel.SidePanelDisplay;

public class ModelState {
    private TimeBook timeBook;
    private ScheduleWindowDisplay scheduleWindowDisplay;
    private SidePanelDisplay sidePanelDisplay;
    private int personIdCounter;
    private int groupIdCounter;



    public ModelState(TimeBook timeBook,
                      ScheduleWindowDisplay scheduleWindowDisplay,
                      SidePanelDisplay sidePanelDisplay,
                      int personIdCounter,
                      int groupIdCounter){

        this.timeBook = timeBook;
        this.scheduleWindowDisplay = scheduleWindowDisplay;
        this.sidePanelDisplay = sidePanelDisplay;
        this.personIdCounter = personIdCounter;
        this.groupIdCounter = groupIdCounter;
    }

    public TimeBook getTimeBook() {
        return timeBook;
    }

    public ScheduleWindowDisplay getScheduleWindowDisplay() {
        return scheduleWindowDisplay;
    }

    public SidePanelDisplay getSidePanelDisplay() {
        return sidePanelDisplay;
    }

    public int getPersonIdCounter() {
        return personIdCounter;
    }

    public int getGroupIdCounter() {
        return groupIdCounter;
    }
}
