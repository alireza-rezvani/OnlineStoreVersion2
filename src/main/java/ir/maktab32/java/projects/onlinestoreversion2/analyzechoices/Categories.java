package ir.maktab32.java.projects.onlinestoreversion2.analyzechoices;

public class Categories {
    private static String[][] categories;

    public static String[][] getCategories(){
        categories = new String[][]{
                {"Electronic Devices", "Radios", "TVs"},
                {"Readables", "Books", "Magazines"},
                {"Shoes", "Formal Shoes", "Sport Shoes"}
        };

        return categories;
    }
}
