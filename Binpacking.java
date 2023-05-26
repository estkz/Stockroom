import java.util.ArrayList;
import java.util.List;

public class Binpacking {
    public static void main(String[] args) {
        // Example Items - Displayed in Kilograms
        int[] items = {90, 80, 70, 60, 50, 40, 30, 20, 10};
        int binCapacity = 100;
        List<List<Integer>> bins = bestFitDecreasing(items, binCapacity);
        System.out.println("Number of bins required: " + bins.size());
        for (int i = 0; i < bins.size(); i++) {
            System.out.println("Bin " + (i + 1) + ": " + bins.get(i));
        }
    }

    public static List<List<Integer>> bestFitDecreasing(int[] items, int binCapacity) {
        List<List<Integer>> bins = new ArrayList<>();
        List<Integer> binRemainingCapacity = new ArrayList<>();
        for (int item : items) {
            int j = 0;
            // Find best bin where the item(s) fit
            for (j = 0; j < binRemainingCapacity.size(); j++) {
                if (binRemainingCapacity.get(j) >= item) {
                    binRemainingCapacity.set(j, binRemainingCapacity.get(j) - item);
                    bins.get(j).add(item);
                    break;
                }
            }
            // If no bin has the ability to store an item, create a new bin
            if (j == binRemainingCapacity.size()) {
                binRemainingCapacity.add(binCapacity - item);
                List<Integer> newBin = new ArrayList<>();
                newBin.add(item);
                bins.add(newBin);
            }
        }
        return bins;
    }
}