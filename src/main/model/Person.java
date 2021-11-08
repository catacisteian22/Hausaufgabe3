package main.model;

/**
 * @author sncam
 */
public class Person {

    public String firstName;
    public String lastName;

    public Person(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    /**
     * @return lastName
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * @param lastName of the person
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * @return firstName
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * @param firstName of the person
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

}
