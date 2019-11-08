package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.logic.commands.UndoCommand;
import seedu.address.logic.parser.exceptions.ParseException;

public class UndoCommandParser implements Parser<UndoCommand> {
    @Override
    public UndoCommand parse(String args) throws ParseException {

        if(!args.trim().equals("")) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, UndoCommand.MESSAGE_USAGE));
        } else {
            return new UndoCommand();
        }
    }
}
