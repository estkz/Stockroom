import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Voorraad.getDatabasePlekken();
        Database db = new Database();
//        new Frame();

        ArrayList<Integer> arr =  db.getItemArrayList(4);

        System.out.println(Arrays.toString(TSP.TSPAlgorithm(arr)));
    }
}