import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Voorraad.getDatabasePlekken();
        Database db = new Database();
        ArrayList<Integer> arr = db.getItemArrayList(4);
//        new Frame();


        System.out.println(Arrays.deepToString(TSP.TSPAlgorithm(arr)));
    }
}