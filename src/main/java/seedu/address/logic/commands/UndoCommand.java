package seedu.address.logic.commands;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.NothingToUndoException;

public class UndoCommand extends Command {

    public static final String COMMAND_WORD = "undo";

    public static final String MESSAGE_USAGE = COMMAND_WORD;

    public static final String MESSAGE_SUCCESS = "Undo Success: ";
    public static final String MESSAGE_FAILURE = "Undo Failure";

    @Override
    public CommandResult execute(Model model) throws CommandException {
        try {
            model.undo();
            return new CommandResult(MESSAGE_SUCCESS);
        } catch (NothingToUndoException e) {
            return new CommandResult(MESSAGE_FAILURE);
        }
    }

    @Override
    public boolean equals(Command command) {
        return false;
    }
}
