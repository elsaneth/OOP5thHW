package Tooted;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Juust extends Piimatoode {

    public Juust(String nimi, double hind) {
        this.nimi = nimi;
        this.hind = hind;
    }

    public double getPrice(double koguKogus, double tooteKogus) {
        double pricePerUnit = this.hind;
        double totalCost = pricePerUnit * tooteKogus;

        if (koguKogus >= 3) {
            totalCost *= 0.9;
        }

        BigDecimal bd = new BigDecimal(totalCost).setScale(2, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }
}

