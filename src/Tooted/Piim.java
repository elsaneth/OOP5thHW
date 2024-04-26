package Tooted;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static java.lang.Math.round;


public class Piim extends Piimatoode {
    /**
     * Constructs a new Piim (milk product) with the specified name and base price.
     *
     * @param nimi The name of the milk product.
     * @param hind The base price of the milk product.
     */
    public Piim(String nimi, double hind) {
        this.nimi = nimi;
        this.hind = hind;
    }

    /**
     * Calculates the total price for a specific quantity of this milk product.
     * Applies a discount if the total quantity of the product is 5 units or more.
     * The resulting price is rounded to two decimal places using RoundingMode.HALF_UP.
     *
     * @param koguKogus The total quantity of the product purchased.
     * @param tooteKogus The specific quantity of this milk product.
     * @return The total price for the given quantity, with discount applied if applicable.
     */
    public double getPrice(double koguKogus, double tooteKogus) {
        double pricePerUnit = this.hind; // Price per unit of the product
        double totalCost = pricePerUnit * tooteKogus; // Calculate total cost for the given quantity

        // Apply a 10% discount if total quantity is 5 or more
        if (koguKogus >= 5) {
            totalCost *= 0.9;
        }

        // Round the total cost to two decimal places
        BigDecimal bd = new BigDecimal(totalCost).setScale(2, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }
}
