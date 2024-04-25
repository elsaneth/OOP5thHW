package Tooted;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Kohupiim extends Piimatoode {
    String nimi;
    double hind;

    public Kohupiim(String nimi, double hind) {
        this.nimi = nimi;
        this.hind = hind;
    }

    public double getPrice(double koguKogus, double tooteKogus) {
        double pricePerUnit = this.hind; // Get the original price per unit
        double totalCost = pricePerUnit * tooteKogus; // Calculate the total cost without discount

        if (koguKogus >= 2) {
            // Apply 10% discount if the quantity is 3 or more
            totalCost *= 0.9; // Apply discount to total cost
        }

        BigDecimal bd = new BigDecimal(totalCost).setScale(2, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }
}
