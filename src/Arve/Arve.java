package Arve;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import Tooted.*;

/**
 * The `Arve` class represents an invoice system for managing dairy product orders.
 * It supports adding products to an invoice, calculating the total cost, and generating a text file for the invoice.
 */
public class Arve {
    // Fields to store the client name, product quantities, and invoice number
    String tellija;
    Map<Piimatoode, Double> tootedKogused;
    int arveNr;
    private static final Random random = new Random();

    /**
     * Generates a random invoice number between 10000000 and 99999999.
     *
     * @return A random invoice number.
     */
    private static int leiaJargmine() {
        long min = 10000000L;
        long max = 99999999L;
        return (int) (min + (random.nextDouble() * (max - min)));
    }

    /**
     * Constructs an `Arve` (invoice) with the specified client name.
     * Initializes an empty product-quantity map and assigns a random invoice number.
     *
     * @param tellija The client name for the invoice.
     */
    public Arve(String tellija) {
        this.tellija = tellija;
        this.tootedKogused = new HashMap<>();
        this.arveNr = leiaJargmine();
    }

    /**
     * Returns the invoice number.
     *
     * @return The invoice number.
     */
    public long leiaArveNr() {
        return arveNr;
    }

    /**
     * Returns the client name.
     *
     * @return The client name.
     */
    public String klient() {
        return tellija;
    }

    /**
     * Adds a product with a specified quantity to the invoice.
     *
     * @param toode The product to add.
     * @param kogus The quantity of the product.
     */
    public void lisa(Piimatoode toode, double kogus) {
        tootedKogused.put(toode, kogus);
    }

    /**
     * Calculates the total quantity of cheese in the invoice.
     *
     * @return The total quantity of cheese.
     */
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

    /**
     * Calculates the total cost for the invoice, taking into account potential discounts for bulk orders.
     * Discounts are applied based on the total quantity of different product types. (Calculated before)
     *
     * @return The total cost of the invoice.
     */
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

    /**
     * Marks the invoice as paid by generating a text file containing the invoice details.
     * This includes the client name, invoice number, product information, and total cost.
     */
    public void maksa() {
        File folder = new File("arved");  // Create a File object for the folder
        if (!folder.exists()) {  // Check if the folder doesn't exist
            folder.mkdir();  // Create the folder
        }

        // Create the full path for the file within the "arved" folder
        String filename = String.format("arved/check%d.txt", arveNr);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            writer.write(String.format("Tellija: %s%n", tellija));
            System.out.printf("\nTellija: %s%n%n", tellija);
            writer.write(String.format("Arve nr: %s%n", leiaArveNr()));
            System.out.printf(String.format("Arve nr: %s%n", leiaArveNr()));
            writer.write("--------------------------------------------------------------------------------------------\n");
            System.out.println("--------------------------------------------------------------------------------------------\n");

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
                        System.out.printf("Toode: %-20s Kogus: %-6.2f l    Hind: %-5.2f (Soodustus 10%%)     Täishind: %.2f%n",
                                toode.getNimi(), kogus, tooteHind, toode.getHind(kogus));
                    } else {
                        writer.write(String.format("Toode: %-20s Kogus: %-6.2f kg   Hind: %-5.2f (Soodustus 10%%)     Täishind: %.2f%n",
                                toode.getNimi(), kogus, tooteHind, toode.getHind(kogus)));
                        System.out.printf("Toode: %-20s Kogus: %-6.2f kg   Hind: %-5.2f (Soodustus 10%%)     Täishind: %.2f%n",
                                toode.getNimi(), kogus, tooteHind, toode.getHind(kogus));
                    }
                } else {
                    if (toode instanceof Piim) {
                        writer.write(String.format("Toode: %-20s Kogus: %-6.2f l    Hind: %-10.2f%n",
                                toode.getNimi(), kogus, tooteHind));
                        System.out.printf("Toode: %-20s Kogus: %-6.2f l    Hind: %-10.2f%n",
                                toode.getNimi(), kogus, tooteHind);
                    }
                    else {
                        writer.write(String.format("Toode: %-20s Kogus: %-6.2f kg   Hind: %-10.2f%n",
                                toode.getNimi(), kogus, tooteHind));
                        System.out.printf("Toode: %-20s Kogus: %-6.2f kg   Hind: %-10.2f%n",
                                toode.getNimi(), kogus, tooteHind);
                    }
                }
            }

            writer.write("\n--------------------------------------------------------------------------------------------\n");
            System.out.println("\n--------------------------------------------------------------------------------------------");
            writer.write(String.format("Kogu arve hind: %.2f €", arveKoguhind));
            System.out.printf("Kogu arve hind: %.2f €%n%n", arveKoguhind);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

