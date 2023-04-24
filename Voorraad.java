public class Voorraad {
    int x;

    int[] plekken = {
            0,0,0,0,20,0,0,0,0,4,0,0,0,13,0,0,0,6,0,0,0,0,9,0,0
    };

    int[] getVoorraad(){
        return plekken;
    }

    boolean setVoorraad(int index, int item){
        x = index-1;

        if(x < 0 || x > 25){
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
