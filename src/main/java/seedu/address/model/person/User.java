package seedu.address.model.person;

import java.util.HashSet;
import java.util.Set;

import seedu.address.model.person.schedule.Schedule;
import seedu.address.model.tag.Tag;

/**
 * Represents the User.
 */
public class User extends Person {

    public User(PersonDescriptor personDescriptor) {
        super(personDescriptor, -1);
    }

    public User(PersonId personId,
                Name name,
                Phone phone,
                Email email,
                Address address,
                Remark remark,
                Schedule schedule,
                Set<Tag> tags) {

        super(personId,
                name,
                phone,
                email,
                address,
                remark,
                schedule,
                tags);
    }

    public User copy() {
        User userCopy = new User(
                getPersonId().copy(),
                getName().copy(),
                getPhone().copy(),
                getEmail().copy(),
                getAddress().copy(),
                getRemark().copy(),
                getSchedule().copy(),
                new HashSet<>(getTags())
        );

        return userCopy;
    }

}
