public class Voorraad {
    int[] plekken = {
            0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0
    };


    int[] getVoorraad(){
        return plekken;
    }


    boolean setVoorraad(int index, int item){
        if(index <= 0 || index > 25){
            System.out.println(index + ": de index moet een getal van 1-25 zijn");
            return false;
        }

        if(plekken[index-1] == 0) {
            plekken[index-1] = item;
            return true;
        } else {
            System.out.println("DOET HET NIET");
        }
        return false;
    }
}
