package seedu.address.model;

import javafx.collections.ObservableList;
import seedu.address.model.group.Group;
import seedu.address.model.group.GroupList;
import seedu.address.model.mapping.PersonToGroupMapping;
import seedu.address.model.mapping.PersonToGroupMappingList;
import seedu.address.model.mapping.exceptions.AlreadyInGroupException;
import seedu.address.model.mapping.exceptions.DuplicateMappingException;
import seedu.address.model.person.Person;
import seedu.address.model.person.PersonList;
import seedu.address.model.person.User;

/**
 * Contains all the information about the TimeBook.
 */
public class TimeBook {

    private PersonList personList;
    private GroupList groupList;
    private PersonToGroupMappingList personToGroupMappingList;

    public TimeBook(Person user) {
        this.personList = new PersonList((User) user);
        this.groupList = new GroupList();
        this.personToGroupMappingList = new PersonToGroupMappingList();
    }

    public TimeBook() {
        this.personList = new PersonList();
        this.groupList = new GroupList();
        this.personToGroupMappingList = new PersonToGroupMappingList();
    }

    public TimeBook(PersonList personList,
                    GroupList groupList,
                    PersonToGroupMappingList personToGroupMappingList) {

        this.personList = personList;
        this.groupList = groupList;
        this.personToGroupMappingList = personToGroupMappingList;
    }

    public TimeBook copy() {
        TimeBook timeBookCopy = new TimeBook(
                personList.copy(),
                groupList.copy(),
                personToGroupMappingList.copy()
        );
        return timeBookCopy;
    }

    public void addPerson(Person person) {
        this.personList.addPerson(person);
    }

    public void addGroup(Group group) {
        this.groupList.addGroup(group);
    }

    /**
     * Adds a mapping into TimeBook.
     */
    public void addMapping(PersonToGroupMapping map) {
        try {
            this.personToGroupMappingList.addPersonToGroupMapping(map);
        } catch (DuplicateMappingException | AlreadyInGroupException e) {
            e.printStackTrace();
        }
    }

    public PersonList getPersonList() {
        return this.personList;
    }

    public ObservableList<Person> getUnmodifiablePersonList() {
        return personList.asUnmodifiableObservableList();
    }

    public GroupList getGroupList() {
        return this.groupList;
    }

    public ObservableList<Group> getUnmodifiableGroupList() {
        return groupList.asUnmodifiableObservableList();
    }

    public PersonToGroupMappingList getPersonToGroupMappingList() {
        return this.personToGroupMappingList;
    }

    public ObservableList<PersonToGroupMapping> getUnmodifiableMappingList() {
        return personToGroupMappingList.asUnmodifiableObservableList();
    }
}
