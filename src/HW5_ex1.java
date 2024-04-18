import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class HW5_ex1 {
    public static void main(String[] args) {
        Person person1 = new Person("Elisabeth", "Suits", 1999);
        Person person2 = new Person("Siim", "Salin", 1995);
        Person person3 = new Person("Siim", "Salin", 1995);
        Person person4 = new Person("Siil", "Sala", 1995);
        Person person5 = new Person("Saara", "Mari", 2010);
        Person person6 = new Person("Karu", "Joonas", 1980);
        Person person7 = new Person("Liis", "Viis", 2005);
        Person person8 = new Person("Mõmmi", "Aabits", 1977);

        List<Person> personList = new ArrayList<>();

        personList.add(person1);
        personList.add(person2);
        personList.add(person3);
        personList.add(person4);
        personList.add(person5);
        personList.add(person6);
        personList.add(person7);
        personList.add(person8);

        System.out.println(personList);

        Scanner scan = new Scanner(System.in);
        System.out.println("Enter last name: ");

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

        boolean personExists = false;
        for (Person person : personList) {
            if (Objects.equals((person.lastName).toLowerCase(), lastName.toLowerCase())) {
                System.out.println(person);
                personExists = true;
            }
        }

        if (!personExists) {
            System.out.println("No person with last name '" + lastName + "'");
        }
    }


}
