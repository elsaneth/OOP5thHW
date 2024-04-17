import java.util.ArrayList;
import java.util.List;

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
    }
}
