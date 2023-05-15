//!!!! NOOIT AANROEPEN !!!!

public class Voorraad {

    static int[] plekken = {
            0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0
    };

    static void getDatabasePlekken(){
        Database db = new Database();
        int[] x = db.getItems();

        System.arraycopy(x, 0, plekken, 0, x.length);
    }

    static int[] getVoorraad(){
        getDatabasePlekken();
        return plekken;
    }
}
