import java.util.ArrayList;
import java.util.Arrays;

public class TSP {
    private static final int[] GRID = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25};

    static int[] TSPAlgorithm(ArrayList<Integer> receivedCoordinates){
        int[] coordinates = new int[receivedCoordinates.size()];

        for (int i = 0; i < receivedCoordinates.size(); i++) {
            coordinates[i] = receivedCoordinates.get(i);
        }

        System.out.println(Arrays.toString(coordinates));

        int[] route = calculateBestRoute(coordinates);
        //add to the route array 25 as first and last element
        int[] route2 = new int[route.length + 2];
        route2[0] = 25;
        route2[route2.length - 1] = 25;
        System.arraycopy(route, 0, route2, 1, route.length);
        return route;
    }

    public static int[] calculateBestRoute(int[] coordinates) {
        int[] modifiedCoordinates = new int[coordinates.length + 2];
        modifiedCoordinates[modifiedCoordinates.length - 1] = 25;
        modifiedCoordinates[0] = 25;
        System.arraycopy(coordinates, 0, modifiedCoordinates, 1, coordinates.length);

        int[] route = new int[modifiedCoordinates.length];
        if (modifiedCoordinates.length == 0) {
            return route;
        }

        boolean[] visited = new boolean[modifiedCoordinates.length];
        visited[0] = true;

        for (int i = 1; i < modifiedCoordinates.length; i++) {
            int currentIdx = getNearestNeighbor(modifiedCoordinates, visited, route[i - 1]);
            route[i] = modifiedCoordinates[currentIdx];
            visited[currentIdx] = true;
        }

        route[0]=25;
        return Arrays.copyOfRange(route, 0, route.length);
    }


    private static int getNearestNeighbor(int[] coordinates, boolean[] visited, int current) {
        int minDistance = Integer.MAX_VALUE;
        int minDistanceVertex = -1;

        for (int i = 0; i < coordinates.length; i++) {
            if (!visited[i]) {
                int distance = Math.abs(getIndex(coordinates[i]) - getIndex(current));
                if (distance < minDistance) {
                    minDistance = distance;
                    minDistanceVertex = i;
                }
            }
        }
        return minDistanceVertex;
    }

    private static int getIndex(int coordinate) {
        for (int i = 0; i < GRID.length; i++) {
            if (GRID[i] == coordinate) {
                return i;
            }
        }
        return -1;
    }
}
