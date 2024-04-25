import Arve.Arve;
import Tooted.Juust;
import Tooted.Kohupiim;
import Tooted.Piim;

public class HW5_ex2 {
    public static void main(String[] args) {
        Juust juust1 = new Juust("Eesti Juust", 7.5);
        Juust juust2 = new Juust("Atleet", 6);
        Piim piim1 = new Piim("Alma", 0.65);
        Kohupiim kohupiim1 = new Kohupiim("Estover", 3);

        Arve arve1 = new Arve("Elias");
        arve1.lisa(juust1, 2.0);
        arve1.lisa(juust2, 2.0);
        arve1.lisa(piim1, 6);
        arve1.lisa(kohupiim1, 2.0);

        Arve arve2 = new Arve("Eli");
        arve2.lisa(juust1, 2.0);
        arve2.lisa(juust2, 2.0);
        arve2.lisa(piim1, 6);
        arve2.lisa(kohupiim1, 2.0);


        arve1.maksa();
        arve2.maksa();

    }
}
