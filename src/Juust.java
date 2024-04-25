public class Juust extends Piimatoode {

    public Juust(String nimi, double hind) {
        this.nimi = nimi;
        this.hind = hind;
    }

    public double getPrice(double koguKogus, double tooteKogus) {
        double pricePerUnit = getHind(); // Get the original price per unit
        double totalCost = pricePerUnit * tooteKogus; // Calculate the total cost without discount

        if (koguKogus >= 3) {
            // Apply 10% discount if the quantity is 3 or more
            totalCost *= 0.9; // Apply discount to total cost
        }

        return totalCost;
    }
}

