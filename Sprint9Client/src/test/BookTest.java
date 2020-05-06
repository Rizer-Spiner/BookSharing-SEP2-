package test;

import model.domain.Book;
import model.domain.Student;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class BookTest
{

    private Book book;
    private Student owner;
    @org.junit.Before
    public void setUp() throws Exception {
        owner= new Student("everyone","all","22552255","alll@via.dk");
       book= new Book("booksharing","group4","2019",owner);
    }

    @Test
    public void getTitle() {
        assertEquals("booksharing",book.getTitle());
    }

    @Test
    public void setTitle() {
        book.setTitle("sprint3");
        assertEquals("sprint3",book.getTitle());
    }

    @Test
    public void getAuthor() {
        assertEquals("group4",book.getAuthor());
    }

    @Test
    public void setAuthor() {
        book.setAuthor("guzukhanums");
        assertEquals("guzukhanums",book.getAuthor());
    }

    @Test
    public void getYear() {
        assertEquals("2019",book.getYear());
    }

    @Test
    public void setYear() {
        book.setYear("2020");
        assertEquals("2020",book.getYear());
    }

    @Test
    public void getOwner() {
        assertEquals(owner,book.getOwner());
    }

    @Test
    public void setOwner() {
        Student newOwner= new Student("lili","lomi","21214141","lili@via.dk");
        book.setOwner(newOwner);
        assertEquals(newOwner, book.getOwner());
    }

    @Test
    public void isAvailable() {
        Book b1= new Book("done", "jj","2010");
        assertEquals(true,b1.isAvailable());
        assertEquals(true,book.isAvailable());
    }

    @Test
    public void setAvailable() {
        book.setAvailable(false);
        assertEquals(false, book.isAvailable());
    }

    @Test
    public void toStringForLibrary() {
        System.out.println(book);
    }

    @Test

    public void equals() {
       Student nowner= new Student("everyne","all","22552255","alll@via.dk");
        Book b1= new Book("booksharing","group4","2019",nowner);
        b1.setAvailable(false);
        book.setAvailable(true);
        book.equals(b1);
        assertEquals(false,book.equals(b1));
    }

    @Test
    public void toStringForLibrary1() {
        System.out.println(book.toStringForLibrary());
    }
}