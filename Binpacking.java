import java.util.ArrayList;
import java.util.List;

public class Binpacking {
    public static void main(String[] args) {
        // Example of items sorted in decreasing order (by weight in kg)
        int[] items = {90, 80, 70, 60, 50, 40, 30, 20, 10};
        int binCapacity = 100;
        int binCount = bestFitDecreasing(items, binCapacity);
        System.out.println("Number of bins required: " + binCount);
    }

    public static int bestFitDecreasing(int[] items, int binCapacity) {
        List<Integer> bins = new ArrayList<>();
        for (int item : items) {
            int j = 0;
            // Find best bin where the item can fit in
            for (j = 0; j < bins.size(); j++) {
                if (bins.get(j) >= item) {
                    bins.set(j, bins.get(j) - item);
                    break;
                }
            }
            // If no bin could fit the item, it will create a new bin.
            if (j == bins.size()) {
                bins.add(binCapacity - item);
            }
        }
        return bins.size();
    }
}