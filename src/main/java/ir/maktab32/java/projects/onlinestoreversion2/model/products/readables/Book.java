package ir.maktab32.java.projects.onlinestoreversion2.model.products.readables;

public class Book extends Readable {
    private int bookId;
    private String writer;
    private String isbn;

    public Book(String title, int price, int count, int pages, String publisher, String writer, String isbn) {
        super(title, price, count, pages, publisher);
        this.writer = writer;
        this.isbn = isbn;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    @Override
    public String toString() {
        return
                "\tProduct ID: " + getProductId() + "\t--->\tBook" +
                        ", Title: " + getTitle() +
                        ", Price: " + getPrice() +
                        ", Available Count: " + getCount() +
                        ", Pages: " + getPages() +
                        ", Publisher: " + getPublisher() +
                        ", Writer: " + writer +
                        ", ISBN: " + isbn ;
    }
}
