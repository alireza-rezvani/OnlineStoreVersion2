package ir.maktab32.java.projects.onlinestoreversion2.update;

import ir.maktab32.java.projects.onlinestoreversion2.daoimpl.products.ProductDaoImpl;
import ir.maktab32.java.projects.onlinestoreversion2.model.Cart;
import ir.maktab32.java.projects.onlinestoreversion2.model.products.Product;

public class UpdateDataBase {

    public static void update(Cart currentUserCart){

        int countSum = 0;
        for (int i : currentUserCart.getProductsCountList())
            countSum += i;

        int cartItemId;
        int cartItemCount;
        Product productItemInDataBase;
        Product editedProductToAddToDataBase;

        for (int i = 0; i < currentUserCart.getProductsIdList().size(); i++){
            if (currentUserCart.getProductsIdList().get(i) != 0) {
                cartItemId = currentUserCart.getProductsIdList().get(i);
                cartItemCount = currentUserCart.getProductsCountList().get(i);

                productItemInDataBase = new ProductDaoImpl().findProductById(cartItemId);
                editedProductToAddToDataBase = new Product(
                        productItemInDataBase.getTitle(),
                        productItemInDataBase.getPrice(),
                        productItemInDataBase.getCount() - cartItemCount
                );

                new ProductDaoImpl().editProduct(cartItemId, editedProductToAddToDataBase);

            }

        }


        if (countSum != 0) {
            System.out.println("\t\u2705 DataBase Updated Successfully!");
        }
        else {
            System.out.println("\t\u26a0 No Changes Done in DataBase!");
        }

    }
}
