/**
 * This class represents a person with a first name, last name, and birth year.
 * It provides methods to get and set these attributes, as well as a custom string representation of the person.
 */
public class Person {
    // Fields to store the first name, last name, and birth year of the person
    String firstName;
    String lastName;
    int birthYear;

    /**
     * Constructs a new Person with the specified first name, last name, and birth year.
     *
     * @param firstName The first name of the person.
     * @param secondName The last name of the person.
     * @param birthYear The year of birth of the person.
     */
    public Person(String firstName, String secondName, int birthYear) {
        this.firstName = firstName;
        this.lastName = secondName;
        this.birthYear = birthYear;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getBirthYear() {
        return birthYear;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setBirthYear(int birthYear) {
        this.birthYear = birthYear;
    }

    /**
     * Returns a string representation of the person, including the first name and birth year.
     *
     * @return A string that represents the person.
     */
    @Override
    public String toString() {
        return "\n {Name: " + this.firstName + ", birth year: " + this.birthYear + "}";
    }
}