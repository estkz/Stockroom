public class Voorraad {
    int[] plekken = {
            0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0
    };


    int[] getVoorraad(){
        return plekken;
    }

    void setVoorraad(int index, int item){
        if(index < 0 || index > 25){
            System.out.println(index + ": de index moet een getal van 1-25 zijn");
        }

        if(plekken[index] == 0) {
            plekken[index] = item;
        } else {
            System.out.println("Staat al iets");
        }
    }
}
