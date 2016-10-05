package seedu.whatnow.model.task;

import java.util.Objects;

import seedu.whatnow.commons.util.CollectionUtil;
import seedu.whatnow.model.tag.UniqueTagList;

/**
 * Represents a Task in the whatnow book.
 * Guarantees: details are present and not null, field values are validated.
 */
public class Task implements ReadOnlyTask {

    private Name name;
    private Phone phone;
    private Email email;
    private Address whatnow;

    private UniqueTagList tags;

    /**
     * Every field must be present and not null.
     */
    public Task(Name name, Phone phone, Email email, Address whatnow, UniqueTagList tags) {
        assert !CollectionUtil.isAnyNull(name, phone, email, whatnow, tags);
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.whatnow = whatnow;
        this.tags = new UniqueTagList(tags); // protect internal tags from changes in the arg list
    }

    /**
     * Copy constructor.
     */
    public Task(ReadOnlyTask source) {
        this(source.getName(), source.getPhone(), source.getEmail(), source.getAddress(), source.getTags());
    }

    @Override
    public Name getName() {
        return name;
    }

    @Override
    public Phone getPhone() {
        return phone;
    }

    @Override
    public Email getEmail() {
        return email;
    }

    @Override
    public Address getAddress() {
        return whatnow;
    }

    @Override
    public UniqueTagList getTags() {
        return new UniqueTagList(tags);
    }

    /**
     * Replaces this task's tags with the tags in the argument tag list.
     */
    public void setTags(UniqueTagList replacement) {
        tags.setTags(replacement);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ReadOnlyTask // instanceof handles nulls
                && this.isSameStateAs((ReadOnlyTask) other));
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, phone, email, whatnow, tags);
    }

    @Override
    public String toString() {
        return getAsText();
    }

}