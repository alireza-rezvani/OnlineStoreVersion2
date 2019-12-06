package ir.maktab32.java.projects.onlinestoreversion2.menu;

import ir.maktab32.java.projects.onlinestoreversion2.analyzechoices.Analyze;
import ir.maktab32.java.projects.onlinestoreversion2.model.Cart;

import java.util.Scanner;

public class Menu {
    private static Scanner scanner = new Scanner(System.in);

    public static void displayMenu(Cart currentUserCart){
        System.out.println("\n\u229e MENU:" +
                "\n\t\u2299 a = Add Product" +
                "\n\t\u2299 d = Delete Product" +
                "\n\t\u2299 c = See Cart" +
                "\n\t\u2299 p = Goto Products" +
                "\n\t\u2299 f = Finalize Purchase");

        String choice = scanner.next();

        if (choice.equals("a")){
            System.out.print("\u29bf Enter Product ID to Add: ");
            int productId = scanner.nextInt();
            System.out.print("\u29bf Enter Count: ");
            int count = scanner.nextInt();
            currentUserCart.addProductToCart(productId, count);
            Menu.displayMenu(currentUserCart);
        }
        else if (choice.equals("d")){
            System.out.print("\u29bf Enter Product ID to Delete: ");
            currentUserCart.removeProductFromCart(scanner.nextInt());
            Menu.displayMenu(currentUserCart);
        }
        else if (choice.equals("c")){
            currentUserCart.displayCart();
            Menu.displayMenu(currentUserCart);
        }
        else if (choice.equals("p")){
            Analyze.displayProductsToUser();
            Menu.displayMenu(currentUserCart);
        }
        else if (choice.equals("f")){
            int countSum = 0;
            for (int i : currentUserCart.getProductsCountList())
                countSum += i;
            if (countSum != 0) {
                System.out.println("\t\u2705 Your Purchase Done Successfully!");
            }
            else {
                System.out.println("\t\u26a0 No Purchase Done!");
            }
        }
        else {
            System.out.println("\t\u26a0 Invalid Key");
            Menu.displayMenu(currentUserCart);
        }
    }
}
