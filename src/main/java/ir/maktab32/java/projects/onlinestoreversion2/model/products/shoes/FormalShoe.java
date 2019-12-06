package ir.maktab32.java.projects.onlinestoreversion2.model.products.shoes;

import ir.maktab32.java.projects.onlinestoreversion2.utilities.Gender;

public class FormalShoe extends Shoe {
    private int formalShoeId;
    private String material;

    public FormalShoe(String title, int price, int count, Gender gender, int size, String color, String material) {
        super(title, price, count, gender, size, color);
        this.material = material;
    }

    public int getFormalShoeId() {
        return formalShoeId;
    }

    public void setFormalShoeId(int formalShoeId) {
        this.formalShoeId = formalShoeId;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    @Override
    public String toString() {
        return
                "\tProduct ID: " + getProductId() + "\t--->\tFormal Shoe" +
                        ", Title: " + getTitle() +
                        ", Price: " + getPrice() +
                        ", Available Count: " + getCount() +
                        ", Gender: " + getGender() +
                        ", Size: " + getSize() +
                        ", Color: " + getColor() +
                        ", Material: " + material ;
    }
}
