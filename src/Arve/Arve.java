package Arve;

import java.util.HashMap;
import java.util.Map;
import Tooted.*;

public class Arve {
    String tellija;
    Map<Piimatoode, Double> tootedKogused;
    int arveNr;
    private static int jargmineArveNr = 1;
    public String klient() {
        return tellija;
    }

    public int leiaArveNr() {
        return arveNr;
    }

    private static int leiaJargmine() {
        return jargmineArveNr++;
    }

    public Arve(String tellija) {
        this.tellija = tellija;
        this.tootedKogused = new HashMap<>();
        this.arveNr = leiaJargmine();
    }

    public void lisa(Piimatoode toode, double kogus) {
        tootedKogused.put(toode, kogus);
    }

    private double arvutaJuustudeKogumaht() {
        double kogumaht = 0;
        for (Map.Entry<Piimatoode, Double> entry : tootedKogused.entrySet()) {
            if (entry.getKey() instanceof Juust) {
                kogumaht += entry.getValue();
            }
        }
        return kogumaht;
    }

    private double arvutaPiimaKogumaht() {
        double kogumaht = 0;
        for (Map.Entry<Piimatoode, Double> entry : tootedKogused.entrySet()) {
            if (entry.getKey() instanceof Piim) {
                kogumaht += entry.getValue();
            }
        }
        return kogumaht;
    }

    private double arvutaKohupiimaKogumaht() {
        double kogumaht = 0;
        for (Map.Entry<Piimatoode, Double> entry : tootedKogused.entrySet()) {
            if (entry.getKey() instanceof Kohupiim) {
                kogumaht += entry.getValue();
            }
        }
        return kogumaht;
    }

    public double leiaKoguSumma() {
        double juustudeKogumaht = arvutaJuustudeKogumaht();
        double piimaKogumaht = arvutaPiimaKogumaht();
        double kohupiimaKogumaht = arvutaKohupiimaKogumaht();

        double koguhind = 0;

        for (Map.Entry<Piimatoode, Double> entry : tootedKogused.entrySet()) {
            Piimatoode toode = entry.getKey();
            double kogus = entry.getValue();

            if (toode instanceof Juust) {
                koguhind += ((Juust) toode).getPrice(juustudeKogumaht, kogus);
            } else if (toode instanceof Piim) {
                koguhind += ((Piim) toode).getPrice(piimaKogumaht, kogus);
            } else if (toode instanceof Kohupiim) {
                koguhind += ((Kohupiim) toode).getPrice(kohupiimaKogumaht, kogus);
            }
        }

        return koguhind; // Tagasta kogu arve hind
    }

    public void maksa() {
        System.out.println("Tellija: " + tellija);
        System.out.println("Arve nr: " + leiaArveNr());

        double arveKoguhind = leiaKoguSumma();


        for (Map.Entry<Piimatoode, Double> entry : tootedKogused.entrySet()) {
            Piimatoode toode = entry.getKey();
            double kogus = entry.getValue();
            double tooteHind = 0;

            if (toode instanceof Juust) {
                tooteHind = ((Juust) toode).getPrice(arvutaJuustudeKogumaht(), kogus);
            } else if (toode instanceof Piim) {
                tooteHind = ((Piim) toode).getPrice(arvutaPiimaKogumaht(), kogus);
            } else if (toode instanceof Kohupiim) {
                tooteHind = ((Kohupiim) toode).getPrice(arvutaKohupiimaKogumaht(), kogus);
            }
            if (toode.isSoodustus()) {
                System.out.println("Toode: " + toode.getNimi() + ", Kogus: " + kogus + ", Hind kokku: " + tooteHind + " (Soodustus 10%)"
                + " Täishind: " + toode.getHind(kogus));
            } else {
                System.out.println("Toode: " + toode.getNimi() + ", Kogus: " + kogus + ", Hind kokku: " + tooteHind);
            }
        }
        System.out.println("Kogu arve hind: " + arveKoguhind);
    }
}

