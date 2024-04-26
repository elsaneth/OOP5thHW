package Arve;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
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
        String filename = String.format("check_%s_%d.txt", tellija.toLowerCase(), arveNr);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            writer.write(String.format("Tellija: %s%n", tellija));
            writer.write(String.format("Arve nr: %s%n", leiaArveNr()));
            writer.write("----------------------------------------------------------------------------------------\n");

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

                if (tooteHind != toode.getHind(kogus)) {
                    if(toode instanceof Piim) {
                        writer.write(String.format("Toode: %-20s Kogus: %-6.2f l    Hind: %-5.2f (Soodustus 10%%)     Täishind: %.2f%n",
                                toode.getNimi(), kogus, tooteHind, toode.getHind(kogus)));
                    } else {
                        writer.write(String.format("Toode: %-20s Kogus: %-6.2f kg   Hind: %-5.2f (Soodustus 10%%)     Täishind: %.2f%n",
                                toode.getNimi(), kogus, tooteHind, toode.getHind(kogus)));
                    }
                } else {
                    if (toode instanceof Piim) {
                        writer.write(String.format("Toode: %-20s Kogus: %-6.2f l    Hind: %-10.2f%n",
                                toode.getNimi(), kogus, tooteHind));
                    }
                    else {
                        writer.write(String.format("Toode: %-20s Kogus: %-6.2f kg   Hind: %-10.2f%n",
                                toode.getNimi(), kogus, tooteHind));
                    }
                }
            }

            writer.write("----------------------------------------------------------------------------------------\n");
            writer.write(String.format("Kogu arve hind: %.2f%n", arveKoguhind));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

