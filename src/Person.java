public class Person {
    String firstName;
    String lastName;
    int birthYear;

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

    @Override
    public String toString() {
        return "\n {Name: " + this.firstName + " " + this.lastName + ", birth year: " + this.birthYear + "}";
    }
}