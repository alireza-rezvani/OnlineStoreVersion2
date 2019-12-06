package ir.maktab32.java.projects.onlinestoreversion2;

import ir.maktab32.java.projects.onlinestoreversion2.analyzechoices.Analyze;
import ir.maktab32.java.projects.onlinestoreversion2.menu.Menu;
import ir.maktab32.java.projects.onlinestoreversion2.model.User;
import ir.maktab32.java.projects.onlinestoreversion2.update.UpdateDataBase;
import ir.maktab32.java.projects.onlinestoreversion2.userentrance.UserEntrance;

import java.util.Scanner;

public class OnlineStoreApp {
    public static void main(String[] args){

        Scanner scanner = new Scanner(System.in);

        User currentUser = new UserEntrance().enter();

        if (currentUser != null){
            Analyze.displayProductsToUser();
            Menu.displayMenu(currentUser.getCart());

            UpdateDataBase.update(currentUser.getCart());
            currentUser.getCart().emptyCart();
        }

        System.out.println("\t\u2705 Have a Nice Day!");

    }
}
