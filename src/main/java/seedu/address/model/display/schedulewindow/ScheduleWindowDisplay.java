package seedu.address.model.display.schedulewindow;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import seedu.address.model.display.exceptions.PersonTimeslotNotFoundException;
import seedu.address.model.display.sidepanel.GroupDisplay;
import seedu.address.model.display.sidepanel.PersonDisplay;
import seedu.address.model.person.Name;
import seedu.address.model.person.exceptions.InvalidTimeslotException;
import seedu.address.model.person.exceptions.PersonNotFoundException;

/**
 * Main window display model.
 */
public class ScheduleWindowDisplay {
    private ArrayList<PersonSchedule> personSchedules;
    private ScheduleWindowDisplayType scheduleWindowDisplayType;
    private GroupDisplay groupDisplay;
    private Optional<List<Name>> filteredNames = Optional.empty();

    private ArrayList<FreeSchedule> freeScheduleWeeks;

    public ScheduleWindowDisplay(ArrayList<PersonSchedule> personSchedules,
                                 ScheduleWindowDisplayType detailWindowDisplayType) {
        this(personSchedules, null, null, detailWindowDisplayType);
    }

    public ScheduleWindowDisplay(ArrayList<PersonSchedule> personSchedules,
                                 ArrayList<FreeSchedule> freeScheduleWeeks, GroupDisplay groupDisplay,
                                 ScheduleWindowDisplayType scheduleWindowDisplayType) {

        this.personSchedules = personSchedules;
        this.freeScheduleWeeks = freeScheduleWeeks;
        this.groupDisplay = groupDisplay;
        this.scheduleWindowDisplayType = scheduleWindowDisplayType;
    }

    public ScheduleWindowDisplayType getScheduleWindowDisplayType() {
        return scheduleWindowDisplayType;
    }

    public ArrayList<PersonSchedule> getPersonSchedules() {
        return personSchedules;
    }

    public GroupDisplay getGroupDisplay() {
        return groupDisplay;
    }

    public ArrayList<FreeSchedule> getFreeSchedule() {
        return freeScheduleWeeks;
    }

    public Optional<List<Name>> getFilteredNames() {
        return filteredNames;
    }

    public void setFilteredNames(List<Name> filteredNames) {
        assert this.scheduleWindowDisplayType.equals(ScheduleWindowDisplayType.GROUP);
        List<Name> presentNames = personSchedules.stream()
                .map(personSchedule -> personSchedule.getPersonDisplay().getName())
                .filter(filteredNames::contains)
                .collect(Collectors.toCollection(ArrayList::new));
        this.filteredNames = Optional.of(presentNames);
    }

    public ArrayList<PersonDisplay> getPersonDisplays() {
        ArrayList<PersonDisplay> personDisplays = new ArrayList<>();
        for (PersonSchedule p : personSchedules) {
            personDisplays.add(p.getPersonDisplay());
        }
        return personDisplays;
    }

    public FreeTimeslot getFreeTimeslot(int week, int id) throws InvalidTimeslotException {
        return freeScheduleWeeks.get(week).getFreeTimeslot(id);
    }

    public PersonTimeslot getPersonTimeslot(Name name, int week, int id)
            throws PersonNotFoundException, PersonTimeslotNotFoundException {

        // find the schedule of the person with the given name
        PersonSchedule selectedPersonSchedule = null;
        for (int i = 0; i < personSchedules.size(); i++) {
            if (personSchedules.get(i).getPersonDisplay().getName().equals(name)) {
                selectedPersonSchedule = personSchedules.get(i);
            }
        }

        if (selectedPersonSchedule == null) {
            throw new PersonNotFoundException();
        }

        // get the weekSchedule
        WeekSchedule currentWeekSchedule = selectedPersonSchedule.getScheduleDisplay().get(week);

        // get the selected timeslot
        PersonTimeslot personTimeslot = currentWeekSchedule.getPersonTimeslot(id);
        return personTimeslot;
    }

    public PersonTimeslot getPersonTimeslotForToday(Name name, int id)
            throws PersonNotFoundException, PersonTimeslotNotFoundException {

        PersonSchedule selectedPersonSchedule = null;
        for (int i = 0; i < personSchedules.size(); i++) {
            if (personSchedules.get(i).getPersonDisplay().getName().equals(name)) {
                selectedPersonSchedule = personSchedules.get(i);
            }
        }
        if (selectedPersonSchedule == null) {
            throw new PersonNotFoundException();
        }

        // get the weekSchedule: week is always 0
        WeekSchedule currentWeekSchedule = selectedPersonSchedule.getScheduleDisplay().get(0);

        // get the selected timeslot
        PersonTimeslot personTimeslot = currentWeekSchedule.getPersonTimeslotForToday(id);
        return personTimeslot;
    }

}
