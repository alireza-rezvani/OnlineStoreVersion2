package ir.maktab32.java.projects.onlinestoreversion2.daoimpl.products.readables;

import ir.maktab32.java.projects.onlinestoreversion2.dao.products.readables.BookDao;
import ir.maktab32.java.projects.onlinestoreversion2.model.products.readables.Book;
import ir.maktab32.java.projects.onlinestoreversion2.model.products.readables.Readable;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookDaoImpl extends ReadableDaoImpl implements BookDao {
    public void addBook(Book book) {
        try {
            super.addReadable(book);
            int readableId = super.findAllReadables()
                    .get(super.findAllReadables().size()-1).getReadableId();

            sql = "insert into books(writer, isbn, readable_id) values(?,?,?)";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, book.getWriter());
            preparedStatement.setString(2, book.getIsbn());
            preparedStatement.setInt(3, readableId);
            preparedStatement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteBook(int bookId) {
        try {
            int readableId = findBookById(bookId).getReadableId();

            sql = "delete from books where id = " + bookId;
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.execute();

            super.deleteReadable(readableId);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void editBook(int bookId, Book book) {
        try {
            int readableId = findBookById(bookId).getReadableId();
            super.editReadable(readableId,book);

            sql = "update books set writer = ?, isbn = ? where id = " + bookId;
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, book.getWriter());
            preparedStatement.setString(2,book.getIsbn());

            preparedStatement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Book findBookById(int bookId) {
        Book resultBook = null;
        try {
            int readableId = 0;
            String writer = "";
            String isbn = "";

            sql = "select * from books where id = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, bookId);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                writer = resultSet.getString("writer");
                isbn = resultSet.getString("isbn");
                readableId = resultSet.getInt("readable_id");
            }

            Readable readable = super.findReadableById(readableId);
            String title = readable.getTitle();
            int price = readable.getPrice();
            int count = readable.getCount();
            int pages = readable.getPages();
            String publisher = readable.getPublisher();
            int productId = readable.getProductId();

            resultBook = new Book(title, price, count, pages, publisher, writer, isbn);

            resultBook.setProductId(productId);
            resultBook.setReadableId(readableId);
            resultBook.setBookId(bookId);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultBook;
    }

    public List<Book> findAllBooks() {
        List<Book> resultBooksList = new ArrayList<Book>();
        try {
            int bookId;
            String writer;
            String isbn;
            int readableId;
            Readable readable;
            String title;
            int price;
            int count;
            int pages;
            String publisher;
            int productId;

            sql = "select * from books";
            preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                bookId = resultSet.getInt("id");
                writer = resultSet.getString("writer");
                isbn = resultSet.getString("isbn");
                readableId = resultSet.getInt("readable_id");
                readable = super.findReadableById(readableId);
                title = readable.getTitle();
                price = readable.getPrice();
                count = readable.getCount();
                pages = readable.getPages();
                publisher = readable.getPublisher();
                productId = readable.getProductId();

                resultBooksList.add(new Book(title, price, count, pages, publisher, writer, isbn));

                resultBooksList.get(resultBooksList.size()-1).setProductId(productId);
                resultBooksList.get(resultBooksList.size()-1).setReadableId(readableId);
                resultBooksList.get(resultBooksList.size()-1).setBookId(bookId);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultBooksList;
    }
}
