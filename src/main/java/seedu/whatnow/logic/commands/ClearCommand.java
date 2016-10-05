package seedu.whatnow.logic.commands;

import seedu.whatnow.model.WhatNow;

/**
 * Clears the whatnow book.
 */
public class ClearCommand extends Command {

    public static final String COMMAND_WORD = "clear";
    public static final String MESSAGE_SUCCESS = "Address book has been cleared!";

    public ClearCommand() {}


    @Override
    public CommandResult execute() {
        assert model != null;
        model.resetData(WhatNow.getEmptyWhatNow());
        return new CommandResult(MESSAGE_SUCCESS);
    }
}