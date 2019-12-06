package ir.maktab32.java.projects.onlinestoreversion2.model;

import ir.maktab32.java.projects.onlinestoreversion2.analyzechoices.Analyze;
import ir.maktab32.java.projects.onlinestoreversion2.daoimpl.CartDaoImpl;
import ir.maktab32.java.projects.onlinestoreversion2.daoimpl.products.ProductDaoImpl;
import ir.maktab32.java.projects.onlinestoreversion2.model.products.Product;

import java.util.ArrayList;
import java.util.List;

public class Cart {
    private int id;
    private List<Integer> productsIdList = new ArrayList<Integer>();
    private List<Integer> productsCountList = new ArrayList<Integer>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Integer> getProductsIdList() {
        return productsIdList;
    }

    public void setProductsIdList(List<Integer> productsIdList) {
        this.productsIdList = productsIdList;
    }

    public List<Integer> getProductsCountList() {
        return productsCountList;
    }

    public void setProductsCountList(List<Integer> productsCountList) {
        this.productsCountList = productsCountList;
    }

    public void addProductToCart(int productId, int count){

        boolean isThisProductInDataBase = false;
        for (Product i : new ProductDaoImpl().findAllProducts()){
            if (i.getProductId() == productId){
                isThisProductInDataBase = true;
                break;
            }
        }

        if (isThisProductInDataBase == false){
            System.out.println("\t\u26a0 Entered Product Doesn't Exist!");
        }
        else {

            while (productsIdList.contains(0)) {
                productsIdList.remove((Integer) 0);
                productsCountList.remove((Integer) 0);
            }

            if (productsIdList.contains(productId)) {
                for (int i = 0; i < productsIdList.size(); i++) {
                    if (productsIdList.get(i) == productId) {
                        if (new ProductDaoImpl().findProductById(productId).getCount() >= productsCountList.get(i) + count) {
                            productsCountList.set(i, productsCountList.get(i) + count);

                            while (productsIdList.size() < 5)
                                productsIdList.add(0);
                            while (productsCountList.size() < 5)
                                productsCountList.add(0);

                            new CartDaoImpl().editCart(this.id, this);
                            System.out.println("\t\u2705 Product Added To Your Cart Successfully!");
                        } else {
                            System.out.println("\t\u26a0 Requested Product Count is not Available!");
                        }
                    }
                }

            } else if (productsIdList.size() < 5) {

                if (count <= new ProductDaoImpl().findProductById(productId).getCount()) {
                    productsIdList.add(productId);
                    productsCountList.add(count);

                    while (productsIdList.size() < 5)
                        productsIdList.add(0);
                    while (productsCountList.size() < 5)
                        productsCountList.add(0);

                    new CartDaoImpl().editCart(this.id, this);
                    System.out.println("\t\u2705 Product Added To Your Cart Successfully!");
                } else {
                    System.out.println("\t\u26a0 Requested Product Count is not Available!");
                }

            } else {
                System.out.println("\t\u26a0 Your Cart is Full(5 Items)! You Only Can Add to Existing Items!");
            }
        }
    }

    public void removeProductFromCart(int productId){
        boolean isThisIdInCart = false;
        for (int i = 0; i < productsIdList.size(); i++){
            if (productsIdList.get(i) == productId){
                productsIdList.remove(i);
                productsCountList.remove(i);

                while (productsIdList.size() <5)
                    productsIdList.add(0);
                while (productsCountList.size() <5)
                    productsCountList.add(0);

                new CartDaoImpl().editCart(this.id,this);
                isThisIdInCart = true;
                System.out.println("\t\u2705 Product Deleted From Your Cart Successfully!");
            }
        }
        if (isThisIdInCart == false){
            System.out.println("\t\u26a0 There is not Such Product in the Cart!");
        }
    }

    public void displayCart(){
        Product product;
        int priceSum = 0;

        while (productsIdList.contains(0)) {
            productsIdList.remove((Integer) 0);
            productsCountList.remove((Integer) 0);
        }

        System.out.println("\u29bf Your Cart: ");
        if (!productsCountList.isEmpty()) {
            for (int i = 0; i < productsIdList.size(); i++) {
                product = Analyze.getChosenProduct(productsIdList.get(i));
                priceSum += product.getPrice() * productsCountList.get(i);
                System.out.println("\t\u2705 Count: " + productsCountList.get(i) + " ---> " + product);
            }
            System.out.println("\t\u2140 Total Price: " + priceSum);
        }
        else {
            System.out.println("\t\u26a0 Your Purchase Cart is Empty!");
        }

        while (productsIdList.size() < 5)
            productsIdList.add(0);
        while (productsCountList.size() < 5)
            productsCountList.add(0);
    }

    public void emptyCart(){


        int countSum = 0;
        for (int i : productsCountList)
            countSum += i;



        for (int i = 0; i < 5; i++){
            productsIdList.set(i, 0);
            productsCountList.set(i, 0);
        }

        new CartDaoImpl().editCart(this.id,this);


        if (countSum != 0) {
            System.out.println("\t\u2705 Purchase Cart Updated Successfully!");
        }
        else {
            System.out.println("\t\u26a0 No Changes Done in Purchase Cart!");
        }
    }
}
