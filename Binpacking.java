import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Binpacking {

    public static List<List<Integer>> bestFitDecreasing(int[] items) {
        int binCapacity = 10;
        int[] otherBinCapacities = {20, 50, 100, 200, 500};

        // Sort items - Decreasing order
        List<Integer> sortedItems = new ArrayList<>();
        for (int item : items) {
            sortedItems.add(item);
            int j = 0;
            while (item > binCapacity){
                binCapacity = otherBinCapacities[j];
                j++;
            }
        }
        sortedItems.sort(Collections.reverseOrder());

        // List of bins
        List<List<Integer>> bins = new ArrayList<>();
        bins.add(new ArrayList<>()); // Add first bin

        // Add each item in the first available/best bin
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

    // Calculate sum of items in bin
    private static int binSum(List<Integer> bin) {
        int sum = 0;
        for (int item : bin) {
            sum += item;
        }
        return sum;
    }

    // Example in console - Used for testing the HMI | (run it as current file for it to work, DO NOT RUN MAIN)
    public static void main(String[] args) {
        int[] items = {5, 5, 6, 4, 2, 3}; // In kg

        List<List<Integer>> bins = bestFitDecreasing(items);

        // Output de resulterende bakken
        for (int i = 0; i < bins.size(); i++) {
            System.out.println("Bin " + (i + 1) + ": " + bins.get(i));
        }
    }
}