public class Main {
    public static void main(String[] args) {
        Orders.setOrderCheck(1, 2, 5, 3);
        Orders.setOrderCheck(2, 6, 3, 5);
        Orders.setOrderCheck(3, 4,6,2);
        Orders.setOrderCheck(5, 6, 3, 5);
        Orders.setOrderCheck(6, 4,6,2);

        boolean res1 = Voorraad.setVoorraad(3,6);
        boolean res2 = Voorraad.setVoorraad(24,3);
        boolean res3 = Voorraad.setVoorraad(1,10);
        boolean res4 = Voorraad.setVoorraad(15,52);
        boolean res5 = Voorraad.setVoorraad(12,20);
        boolean res6 = Voorraad.setVoorraad(25,19);




        new Frame();

//        Orders orders = new Orders();
//
//        orders.setOrderCheck(1, 1, 24,12);
//        orders.setOrderCheck(2, 53,10,4);
//
//        orders.getOrders();
    }
}