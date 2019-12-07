package ir.maktab32.java.projects.onlinestoreversion2.analyzechoices;

import ir.maktab32.java.projects.onlinestoreversion2.daoimpl.products.electronicdevices.RadioDaoImpl;
import ir.maktab32.java.projects.onlinestoreversion2.daoimpl.products.electronicdevices.TVDaoImpl;
import ir.maktab32.java.projects.onlinestoreversion2.daoimpl.products.readables.BookDaoImpl;
import ir.maktab32.java.projects.onlinestoreversion2.daoimpl.products.readables.MagazineDaoImpl;
import ir.maktab32.java.projects.onlinestoreversion2.daoimpl.products.shoes.FormalShoeDaoImpl;
import ir.maktab32.java.projects.onlinestoreversion2.daoimpl.products.shoes.SportShoeDaoImpl;
import ir.maktab32.java.projects.onlinestoreversion2.model.products.Product;
import ir.maktab32.java.projects.onlinestoreversion2.model.products.electronicdevices.Radio;
import ir.maktab32.java.projects.onlinestoreversion2.model.products.electronicdevices.TV;
import ir.maktab32.java.projects.onlinestoreversion2.model.products.readables.Book;
import ir.maktab32.java.projects.onlinestoreversion2.model.products.readables.Magazine;
import ir.maktab32.java.projects.onlinestoreversion2.model.products.shoes.FormalShoe;
import ir.maktab32.java.projects.onlinestoreversion2.model.products.shoes.SportShoe;
import ir.maktab32.java.projects.onlinestoreversion2.utilities.Utilities;

import java.util.List;
import java.util.Scanner;

public class Analyze {
    private  static Scanner scanner = new Scanner(System.in);

    private static String[][] categories = Categories.getCategories();

    public static void displayMainCategories(){

        System.out.println("\u29bf Products' Category:");
        for (int i = 0; i < categories.length; i++)
            System.out.println("\t" + (i+1) + ". " + categories[i][0]);
    }

    public static void displaySubCategories(int mainCategoryChoice){

        System.out.println("\u29bf " + categories[mainCategoryChoice-1][0] + ":");
        for (int i = 1; i < categories[mainCategoryChoice-1].length; i++)
            System.out.println("\t" + i + ". " + categories[mainCategoryChoice-1][i]);
    }

    public static void displayChosenSubcategoryItems(int mainCategoryChoice, int subCategoryChoice){

        String chosenSubCategory = categories[mainCategoryChoice-1][subCategoryChoice];

        System.out.println("\u29bf " + chosenSubCategory + ":\t(Input ID to Select)");
        switch (chosenSubCategory){
            case "Radios":
                List<Radio> radios = new RadioDaoImpl().findAllRadios();
                radios.forEach(System.out::println);
                if (radios.isEmpty())
                    System.out.println("\t\u26a0 There Are no Radios in DataBase!");
                break;
            case "TVs":
                List<TV> tvs = new TVDaoImpl().findAllTVs();
                tvs.forEach(System.out::println);
                if (tvs.isEmpty())
                    System.out.println("\t\u26a0 There Are no TVs in DataBase!");
                break;
            case "Books":
                List<Book> books = new BookDaoImpl().findAllBooks();
                books.forEach(System.out::println);
                if (books.isEmpty())
                    System.out.println("\t\u26a0 There Are no Books in DataBase!");
                break;
            case "Magazines":
                List<Magazine> magazines = new MagazineDaoImpl().findAllMagazines();
                magazines.forEach(System.out::println);
                if (magazines.isEmpty())
                    System.out.println("\t\u26a0 There Are no Magazines in DataBase!");
                break;
            case "Formal Shoes":
                List<FormalShoe> formalShoes = new FormalShoeDaoImpl().findAllFormalShoes();
                formalShoes.forEach(System.out::println);
                if (formalShoes.isEmpty())
                    System.out.println("\t\u26a0 There Are no Formal Shoes in DataBase!");
                break;
            case "Sport Shoes":
                List<SportShoe> sportShoes = new SportShoeDaoImpl().findAllSportShoes();
                sportShoes.forEach(System.out::println);
                if (sportShoes.isEmpty())
                    System.out.println("\t\u26a0 There Are no Sport Shoes in DataBase!");
                break;
            default:
                System.out.println("\t\u26a0 No Matches in Switch Case! Failed to Find SubCategory!");
                break;
        }
    }

    public static Product getChosenProduct(int productId){

        Product resultProduct = null;

        List<Radio> radios = new RadioDaoImpl().findAllRadios();
        List<TV> tvs = new TVDaoImpl().findAllTVs();
        List<Book> books = new BookDaoImpl().findAllBooks();
        List<Magazine> magazines = new MagazineDaoImpl().findAllMagazines();
        List<FormalShoe> formalShoes = new FormalShoeDaoImpl().findAllFormalShoes();
        List<SportShoe> sportShoes = new SportShoeDaoImpl().findAllSportShoes();

        for (Radio i : radios){
            if (i.getProductId() == productId)
                resultProduct = i;
        }

        for (TV i : tvs){
            if (i.getProductId() == productId)
                resultProduct = i;
        }

        for (Book i : books){
            if (i.getProductId() == productId)
                resultProduct = i;
        }

        for (Magazine i : magazines){
            if (i.getProductId() == productId)
                resultProduct = i;
        }

        for (FormalShoe i : formalShoes){
            if (i.getProductId() == productId)
                resultProduct = i;
        }

        for (SportShoe i : sportShoes){
            if (i.getProductId() == productId)
                resultProduct = i;
        }

        return resultProduct;
    }

    public static void displayProductsToUser(){

        String mainCategoryChoice;
        String subCategoryChoice;

        do {
            Analyze.displayMainCategories();
            mainCategoryChoice = scanner.next();
            if (!Utilities.isNumeric(mainCategoryChoice)
                    || Integer.parseInt(mainCategoryChoice) > categories.length
                    || Integer.parseInt(mainCategoryChoice) < 1){
                System.out.println("\t\u26a0 Invalid Input!\n");
            }
        }while (!Utilities.isNumeric(mainCategoryChoice)
                || Integer.parseInt(mainCategoryChoice) > categories.length
                || Integer.parseInt(mainCategoryChoice) < 1);


        do {
            Analyze.displaySubCategories(Integer.parseInt(mainCategoryChoice));
            subCategoryChoice = scanner.next();
            if (!Utilities.isNumeric(subCategoryChoice)
                    || Integer.parseInt(subCategoryChoice) > categories[Integer.parseInt(mainCategoryChoice)-1].length -1
                    || Integer.parseInt(subCategoryChoice) < 1){
                System.out.println("\t\u26a0 Invalid Input!\n");
            }
        }while (!Utilities.isNumeric(subCategoryChoice)
                || Integer.parseInt(subCategoryChoice) > categories[Integer.parseInt(mainCategoryChoice)-1].length -1
                || Integer.parseInt(subCategoryChoice) < 1);

        Analyze.displayChosenSubcategoryItems(Integer.parseInt(mainCategoryChoice),Integer.parseInt(subCategoryChoice));

    }

}
