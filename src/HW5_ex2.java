import Tooted.Juust;
import Tooted.Kohupiim;
import Tooted.Piim;
import Tooted.Piimatoode;

import java.util.HashMap;
import java.util.Map;

public class HW5_ex2 {
    public static void main(String[] args) {
        Juust juust1 = new Juust("Eesti Tooted.Juust", 7.5);
        Juust juust2 = new Juust("Atleet", 6);
        Piim piim1 = new Piim("Alma", 0.65);
        Kohupiim kohupiim1 = new Kohupiim("Estover", 3);

        Map<Piimatoode, Double> piimatootedKogused = new HashMap<>();
        piimatootedKogused.put(juust1, 2.0);
        piimatootedKogused.put(juust2, 2.0);
        piimatootedKogused.put(piim1, 6.0);
        piimatootedKogused.put(kohupiim1, 1.0);

        double juustudeKogumaht = 0;
        double piimaKogumaht = 0;
        double kohupiimaKogumaht = 0;

        // Loop through the map once to compute total volumes for Tooted.Juust, Tooted.Piim, and Tooted.Kohupiim
        for (Map.Entry<Piimatoode, Double> entry : piimatootedKogused.entrySet()) {
            Piimatoode toode = entry.getKey();
            double kogus = entry.getValue();

            if (toode instanceof Juust) {
                juustudeKogumaht += kogus;
            } else if (toode instanceof Piim) {
                piimaKogumaht += kogus;
            } else if (toode instanceof Kohupiim) {
                kohupiimaKogumaht += kogus;
            }
        }

        double juust1Price = juust1.getPrice(juustudeKogumaht, piimatootedKogused.get(juust1));
        double juust2Price = juust2.getPrice(juustudeKogumaht, piimatootedKogused.get(juust2));
        double piim1Price = piim1.getPrice(piimaKogumaht, piimatootedKogused.get(piim1));
        double kohupiim1Price = kohupiim1.getPrice(kohupiimaKogumaht, piimatootedKogused.get(kohupiim1));

        System.out.println("Juust1: " + juust1Price + " Juust2: " + juust2Price);
        System.out.println("Piim1: " + piim1Price + " kohupiim1: " + kohupiim1Price);
    }
}
