import Arve.Arve;
import Tooted.Juust;
import Tooted.Kohupiim;
import Tooted.Piim;

/**
 * This class represents a simple program that creates various dairy products
 * and adds them to different invoices. It then marks the invoices as paid.
 */
public class HW5_ex2 {

    /**
     * The main method serves as the entry point for this program.
     * It creates instances of various dairy products (Juust, Piim, Kohupiim),
     * adds them to different invoices, and then marks the invoices as paid.
     *
     * @param args Command line arguments, not used in this program.
     */
    public static void main(String[] args) {
        Juust juust1 = new Juust("Juust Gouda", 14.6);
        Juust juust2 = new Juust("Juust Cheddar", 14.87);
        Juust juust3 = new Juust("Juust Grand", 16.99);
        Piim piim1 = new Piim("Piim 2,5%", 1.30);
        Piim piim2 = new Piim("Kitsepiim", 3.7);
        Kohupiim kohupiim1 = new Kohupiim("Kohupiim Ricotta", 4);

        // Create invoices and add products to them with specified quantities
        Arve arve3 = new Arve("Uku");
        arve3.lisa(juust1, 0.2);
        arve3.lisa(juust2, 0.1);
        arve3.lisa(juust3, 0.5);
        arve3.lisa(piim1, 2.5);
        arve3.lisa(piim2, 1.5);
        arve3.lisa(kohupiim1, 2.5);

        Arve arve1 = new Arve("Elisabeth");
        arve1.lisa(juust1, 2.0);
        arve1.lisa(juust2, 2.0);
        arve1.lisa(piim1, 5);
        arve1.lisa(kohupiim1, 2.0);

        Arve arve2 = new Arve("Siim");
        arve2.lisa(juust1, 0.5);
        arve2.lisa(juust3, 1);
        arve2.lisa(piim2, 2);
        arve2.lisa(kohupiim1, 1.0);

        // Mark invoices as paid
        arve1.maksa();
        arve2.maksa();
        arve3.maksa();

    }
}
