package ir.maktab32.java.projects.onlinestoreversion2.model.products.readables;

public class Magazine extends Readable {
    private int magazineId;
    private String editor;

    public Magazine(String title, int price, int count, int pages, String publisher, String editor) {
        super(title, price, count, pages, publisher);
        this.editor = editor;
    }

    public int getMagazineId() {
        return magazineId;
    }

    public void setMagazineId(int magazineId) {
        this.magazineId = magazineId;
    }

    public String getEditor() {
        return editor;
    }

    public void setEditor(String editor) {
        this.editor = editor;
    }

    @Override
    public String toString() {
        return
                "\tProduct ID: " + getProductId() + "\t--->\tMagazine" +
                        ", Title: " + getTitle() +
                        ", Price: " + getPrice() +
                        ", Available Count: " + getCount() +
                        ", Pages: " + getPages() +
                        ", Publisher: " + getPublisher() +
                        ", Editor: " + editor ;
    }
}
