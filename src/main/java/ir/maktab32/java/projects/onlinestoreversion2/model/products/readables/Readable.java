package ir.maktab32.java.projects.onlinestoreversion2.model.products.readables;

import ir.maktab32.java.projects.onlinestoreversion2.model.products.Product;

public class Readable extends Product {
    private int readableId;
    private int pages;
    private String publisher;

    public Readable(String title, int price, int count, int pages, String publisher) {
        super(title, price, count);
        this.pages = pages;
        this.publisher = publisher;
    }

    public int getReadableId() {
        return readableId;
    }

    public void setReadableId(int readableId) {
        this.readableId = readableId;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }
}
