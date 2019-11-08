package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;

import java.time.LocalDateTime;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.display.schedulewindow.ScheduleWindowDisplayType;
import seedu.address.model.display.sidepanel.SidePanelDisplayType;
import seedu.address.model.person.Name;
import seedu.address.model.person.exceptions.PersonNotFoundException;

/**
 * Deletes a person.
 */
public class DeletePersonCommand extends Command {
    public static final String COMMAND_WORD = "deleteperson";

    public static final String MESSAGE_USAGE = COMMAND_WORD + " "
            + PREFIX_NAME + "NAME";

    public static final String MESSAGE_SUCCESS = "Delete person success: %s deleted";
    public static final String MESSAGE_FAILURE = "Unable to delete person: %s";

    public static final String MESSAGE_PERSON_NOT_FOUND = "Unable to find person to delete";

    public final Name name;

    public DeletePersonCommand(Name name) {
        requireNonNull(name);
        this.name = name;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {

        try {
            model.deletePerson(name);

            // update main window display
            model.updateScheduleWindowDisplay(LocalDateTime.now(), ScheduleWindowDisplayType.HOME);

            // update side panel display
            model.updateSidePanelDisplay(SidePanelDisplayType.TABS);

            model.saveState();
            return new CommandResult(String.format(MESSAGE_SUCCESS, name.toString()));

        } catch (PersonNotFoundException e) {
            return new CommandResult(String.format(MESSAGE_FAILURE, MESSAGE_PERSON_NOT_FOUND));
        }

    }

    @Override
    public boolean equals(Command command) {
        if (command == null) {
            return false;
        } else if (!(command instanceof DeletePersonCommand)) {
            return false;
        } else if (((DeletePersonCommand) command).name.equals(this.name)) {
            return true;
        } else {
            return false;
        }
    }
}
