import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.IntStream;

public class TSP {
    private static final int[] GRID = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25};

    static int[][] TSPAlgorithm(ArrayList<Integer> receivedCoordinates){
        int[] coordinates = new int[receivedCoordinates.size()];

        for (int i = 0; i < receivedCoordinates.size(); i++) {
            coordinates[i] = receivedCoordinates.get(i);
        }

        System.out.println(Arrays.toString(coordinates));

        int[] route = new int[coordinates.length];
        System.arraycopy(calculateBestRoute(coordinates), 1, route, 0, coordinates.length);

        int[][] splicedRoute;
        splicedRoute = splitArray(route, 3);

        return route26(splicedRoute);
    }

    public static int[] calculateBestRoute(int[] coordinates) {
        int[] modifiedCoordinates = new int[coordinates.length + 1];
        modifiedCoordinates[0] = 25;
        System.arraycopy(coordinates, 0, modifiedCoordinates, 1, coordinates.length);

        int[] route = new int[modifiedCoordinates.length];

        boolean[] visited = new boolean[modifiedCoordinates.length];
        visited[0] = true;

        for (int i = 1; i < modifiedCoordinates.length; i++) {
            int currentIdx = getNearestNeighbor(modifiedCoordinates, visited, route[i - 1]);
            route[i] = modifiedCoordinates[currentIdx];
            visited[currentIdx] = true;
        }


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

    public static int[][] splitArray(int[] inputArray, int chunkSize) {
        return IntStream.iterate(0, i -> i + chunkSize)
                .limit((int) Math.ceil((double) inputArray.length / chunkSize))
                .mapToObj(j -> Arrays.copyOfRange(inputArray, j, Math.min(inputArray.length, j + chunkSize)))
                .toArray(int[][]::new);
    }

    public static int[][] route26(int[][] splicedRoute){
        int[][] finalRoute = new int[splicedRoute.length][];

        for (int i = 0; i < splicedRoute.length; i++) {
            finalRoute[i] = new int[splicedRoute[i].length+2];
            System.arraycopy(splicedRoute[i], 0, finalRoute[i], 1, splicedRoute[i].length);

            finalRoute[i][0] = 26;
            finalRoute[i][splicedRoute[i].length+1] = 26;
        }
        return finalRoute;
    }
}
