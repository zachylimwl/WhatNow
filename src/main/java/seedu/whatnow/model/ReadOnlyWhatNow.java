package seedu.whatnow.model;


import java.util.List;

import seedu.whatnow.model.tag.Tag;
import seedu.whatnow.model.tag.UniqueTagList;
import seedu.whatnow.model.task.ReadOnlyTask;
import seedu.whatnow.model.task.UniqueTaskList;

/**
 * Unmodifiable view of an whatnow book
 */
public interface ReadOnlyWhatNow {

    UniqueTagList getUniqueTagList();

    UniqueTaskList getUniqueTaskList();

    /**
     * Returns an unmodifiable view of tasks list
     */
    List<ReadOnlyTask> getTaskList();

    /**
     * Returns an unmodifiable view of tags list
     */
    List<Tag> getTagList();

}
