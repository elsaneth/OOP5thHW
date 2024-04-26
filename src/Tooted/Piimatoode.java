package Tooted;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * This abstract class represents a dairy product with a name and a base price.
 * It provides methods to retrieve the product's name and calculate its price based on a given quantity.
 */
public abstract class Piimatoode {
    // Fields to store the name and base price of the dairy product
    String nimi;
    double hind;

    /**
     * Returns the name of the dairy product.
     *
     * @return The name of the dairy product.
     */
    public String getNimi() {
        return nimi;
    }

    public double getHind2() { return hind; }

    /**
     * Calculates the price of the dairy product for a given quantity.
     * The price is rounded to two decimal places using the rounding mode HALF_UP.
     *
     * @param kogus The quantity of the product.
     * @return The calculated price based on the given quantity, rounded to two decimal places.
     */
    public double getHind(Double kogus) {
        BigDecimal bd = BigDecimal.valueOf(hind * kogus).setScale(2, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }
}
