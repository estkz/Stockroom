import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Binpacking {

    public static List<List<Integer>> bestFitDecreasing(int[] items) {
        int binCapacity = 10;
        int[] otherBinCapacities = {20, 50, 100};

        // Sorteer de items in aflopende volgorde
        List<Integer> sortedItems = new ArrayList<>();
        for (int item : items) {
            sortedItems.add(item);
            int j = 0;
            while (item > binCapacity){
                binCapacity = otherBinCapacities[j];
                j++;
            }
        }
        Collections.sort(sortedItems, Collections.reverseOrder());

        // Lijst van bakken
        List<List<Integer>> bins = new ArrayList<>();
        bins.add(new ArrayList<>()); // Voeg de eerste bak toe

        // Plaats elk item in de eerste beschikbare bak waarin het past
        for (int item : sortedItems) {
            boolean placed = false;
            for (List<Integer> bin : bins) {
                if (binCapacity - binSum(bin) >= item) {
                    bin.add(item);
                    placed = true;
                    break;
                }
            }
            if (!placed) {
                List<Integer> newBin = new ArrayList<>();
                newBin.add(item);
                bins.add(newBin);
            }
        }

        return bins;
    }

    // Hulpmethode om de som van de items in een bak te berekenen
    private static int binSum(List<Integer> bin) {
        int sum = 0;
        for (int item : bin) {
            sum += item;
        }
        return sum;
    }

    // Voorbeeldgebruik
    public static void main(String[] args) {
        int[] items = {5, 5, 6, 4, 2, 3}; // In kg


        List<List<Integer>> bins = bestFitDecreasing(items);

        // Output de resulterende bakken
        for (int i = 0; i < bins.size(); i++) {
            System.out.println("Bin " + (i + 1) + ": " + bins.get(i));
        }
    }
}