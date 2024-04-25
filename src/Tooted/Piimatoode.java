package Tooted;

public abstract class Piimatoode {
    String nimi;
    double hind;

    public String getNimi() {
        return nimi;
    }

    public double getHind() {
        return hind;
    }

    public double getPrice(double kogus) {
        return hind * kogus;
    }
}
