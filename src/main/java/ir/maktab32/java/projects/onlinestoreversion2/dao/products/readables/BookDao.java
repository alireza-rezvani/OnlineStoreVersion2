package ir.maktab32.java.projects.onlinestoreversion2.dao.products.readables;

import ir.maktab32.java.projects.onlinestoreversion2.model.products.readables.Book;

import java.util.List;

public interface BookDao {
    void addBook(Book book);
    void deleteBook(int bookId);
    void editBook(int bookId, Book book);
    Book findBookById(int bookId);
    List<Book> findAllBooks();
}
