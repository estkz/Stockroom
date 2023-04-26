public class Voorraad {

    static int[] plekken = {
            0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0
    };

    static int[] getVoorraad(){
        return plekken;
    }

    static boolean setVoorraad(int index, int item){
        int x;
        x = index-1;

        if(index < 1 || index > 25){
            System.out.println(index + ": de index moet een getal van 1-25 zijn");
            return false;
        }

        if(plekken[x] == 0) {
            plekken[x] = item;
            return true;
        } else {
            System.out.println("STAAT AL EEN ITEM");
        }

        return false;
    }
}
