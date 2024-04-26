import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;
/**
 * This class represents a simple console-based application that manages a list of people (Person objects).
 * The application allows users to search for a person by their last name and outputs information about them.
 */
public class HW5_ex1 {
    /**
     * The main method serves as the entry point for the program. It initializes a list of people and prompts the user to search by last name.
     * It outputs information about people with the matching last name or indicates if no match is found.
     *
     * @param args Command line arguments, not used in this program.
     */
    public static void main(String[] args) {
        // Create several Person objects and add them to a list
        Person person1 = new Person("Elisabeth", "Suits", 1999);
        Person person2 = new Person("Siim", "Salin", 1995);
        Person person3 = new Person("Siim", "Salin", 1995);
        Person person4 = new Person("Siil", "Sala", 1995);
        Person person5 = new Person("Saara", "Mari", 2010);
        Person person6 = new Person("Karu", "Mari", 1980);
        Person person7 = new Person("Liis", "Viis", 2005);
        Person person8 = new Person("Mõmmi", "Aabits", 1977);

        // Create a list to store Person objects
        List<Person> inimesed = new ArrayList<>();
        inimesed.add(person1);
        inimesed.add(person2);
        inimesed.add(person3);
        inimesed.add(person4);
        inimesed.add(person5);
        inimesed.add(person6);
        inimesed.add(person7);
        inimesed.add(person8);

        // Output the list of people
        System.out.println(inimesed);

        // Create a scanner to read user input
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter last name: ");

        // Read and validate the user's input for a last name
        String lastName = scan.nextLine();

        boolean valid = false;
        while (!valid) {
            valid = true;
            for (int i = 0; i < lastName.length(); i++) {
                if (!Character.isLetter(lastName.charAt(i))) {
                    System.out.println("Use only letters! Enter last name: ");
                    lastName = scan.nextLine();
                    valid = false;
                    break;
                }
            }
        }

        // Search for a person with the given last name
        boolean personExists = false;
        for (Person person : inimesed) {
            if (Objects.equals((person.lastName).toLowerCase(), lastName.toLowerCase())) {
                System.out.println(person);
                personExists = true;
            }
        }

        // Output if no person with the given last name is found
        if (!personExists) {
            System.out.println("No person with last name '" + lastName + "'");
        }
    }

}
