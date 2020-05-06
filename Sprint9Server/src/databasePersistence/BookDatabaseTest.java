package databasePersistence;

import model.domain.Book;
import model.domain.Request;
import model.domain.Student;
import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;

import static org.junit.Assert.*;

public class BookDatabaseTest {
    private BookDatabase db;
    private Book book;
    private Student user;


    @Before
    public void setUp() throws Exception {
        this.db = new BookDatabase();

    }

    @Test
    public void loadPerson() throws SQLException {
        System.out.println(db.loadPerson().toStringForDB());
    }

    @Test
    public void loadBook() throws SQLException {
        user = new Student("Nicki", "hapo", "22443366", "nini@daycare.dk");
        System.out.println(db.loadBook(user.getUsername()));
    }

    @Test
    public void loadAllBooks() throws SQLException {
        System.out.println(db.loadAllBooks());
    }

    @Test
    public void loadBorrowedBooks() {
        Student user1 = new Student("pooneh", "poma", "25256633", "poon@via.dk");

        System.out.println(db.loadBorrowedBooks(user1.getUsername()));
    }

    @Test
    public void loadRequest() throws SQLException {
        System.out.println(db.loadRequest().toString());
    }

    @Test
    public void removeFromLoan() throws SQLException {
        user = new Student("ati", "jojo", "12121152", "ati@via.dk");

        book = new Book("catDog", "Jane Doe", "2010", user, false);

        db.removeFromLoan(book);

    }

    @Test
    public void registerBook() throws SQLException {
        Student user1 = new Student("pooneh", "poma", "25256633", "poon@via.dk");
        Book newBook = new Book("cake", "Holly Grapes", "2015", user1);
        db.registerBook(newBook);

    }

    @Test
    public void removeBook() throws SQLException {
        Student user1 = new Student("pooneh", "poma", "25256633", "poon@via.dk");
        Book newBook = new Book("cake", "Holly Grapes", "2015", user1);
        Book book1 = new Book("kitten", "Bob Builder", "2018", user1, true);
       db.registerBook(book1);
        user1.registerNewBook(newBook);
        user1.registerNewBook(book1);
        System.out.println(user1.getBookList().getBookList());


        db.removeBook(newBook);
        assertEquals(1, user1.getBookList().getBookList().size());
    }

    @Test
    public void registerPerson() throws SQLException {
        user = new Student("Roxi", "rox", "12345678", "roro@via.dk");
        book = new Book("super girl", "Jane Doe", "2017", user, true);
        db.registerPerson(user, book);
    }

    @Test
    public void getContactInfo() throws SQLException {
        user = new Student("Roxi", "rox", "12345678", "roro@via.dk");


    }
    @Test
    public void getContactInfo1() throws SQLException {
        user = new Student("Roxi", "rox", "12345678", "roro@via.dk");

        System.out.println(db.getContactInfo(user));
    }

    @Test
    public void addRequest() throws SQLException {
        user = new Student("ati", "jojo", "12121152", "ati@via.dk");
        Student user1 = new Student("pooneh", "poma", "25256633", "poon@via.dk");
        book = new Book("catDog", "Jane Doe", "2010", user, true);

        Book book1 = new Book("kitten", "Bob Builder", "2018", user1, true);

        db.registerPerson(user1, book1);
        db.registerPerson(user, book);
        Request request = new Request(user1, book);

        db.addRequest(request);
    }

    @Test
    public void removeRequest() throws SQLException {
        user = new Student("ati", "jojo", "12121152", "ati@via.dk");
        Student user1 = new Student("pooneh", "poma", "25256633", "poon@via.dk");
        book = new Book("catDog", "Jane Doe", "2010", user, true);

        Request request = new Request(user1, book);
        db.removeRequest(request);
    }

    @Test
    public void removeStudent() throws SQLException {
        Student user1 = new Student("pooneh", "poma", "25256633", "poon@via.dk");
        db.removeStudent(user1);
    }

    @Test
    public void removeDeniedRequest() throws SQLException {
        user = new Student("ati", "jojo", "12121152", "ati@via.dk");
        Student user1 = new Student("Roxi", "rox", "12345678", "roro@via.dk");
        book = new Book("catDog", "Jane Doe", "2010", user, true);

        Request request = new Request(user1, book);
        db.removeDeniedRequest(request);
    }

    @Test
    public void updateStatusToDenied() throws SQLException {
        user = new Student("ati", "jojo", "12121152", "ati@via.dk");
        Student user1 = new Student("Roxi", "rox", "12345678", "roro@via.dk");
        book = new Book("catDog", "Jane Doe", "2010", user, true);

        Request request = new Request(user1, book);
        db.updateStatusToDenied(request);
    }

    @Test
    public void updateStatusToAccepted() throws SQLException {
        user = new Student("ati", "jojo", "12121152", "ati@via.dk");
        Student user1 = new Student("pooneh", "poma", "25256633", "poon@via.dk");
        book = new Book("catDog", "Jane Doe", "2010", user, true);

        Request request = new Request(user1, book);
        db.updateStatusToAccepted(request);
    }

    @Test
    public void moveRequestToLoan() throws SQLException {
        user = new Student("ati", "jojo", "12121152", "ati@via.dk");
        Student user1 = new Student("pooneh", "poma", "25256633", "poon@via.dk");
        book = new Book("catDog", "Jane Doe", "2010", user, true);

        Request request = new Request(user1, book);
        db.moveRequestToLoan(request);
    }
}