package Tooted;

import java.math.BigDecimal;
import java.math.RoundingMode;

public abstract class Piimatoode {
    String nimi;
    double hind;

    public String getNimi() {
        return nimi;
    }

    public double getHind(Double kogus) {
        BigDecimal bd = new BigDecimal((hind * kogus)).setScale(2, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }
}
