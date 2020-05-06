package test;

import model.domain.Book;
import model.domain.BookList;
import model.domain.Student;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class StudentTest
{
    private Student student;
    private BookList list;

    @Before
    public void setUp() throws Exception {
        student = new Student("ati", "joje", "91434065", "ati@via.dk");
        list = new BookList();
    }

    @Test
    public void getBookList() {
        Book book = new Book("booksharing", "group4", "2019");
        Book book1 = new Book("java", "hbv", "2019");
        Book book2 = new Book("booksharing", "kol", "2019");
        list.addBook(book);
        list.addBook(book1);
        list.addBook(null);
        list.addBook(book);
        list.addBook(book2);
        student.setBookList(list);
        System.out.println(student.getBookList());
    }

    @Test
    public void setBookList() {
        Book book = new Book("booksharing", "group4", "2019");
        Book book1 = new Book("java", "hbv", "2019");
        Book book2 = new Book("booksharing", "kol", "2019");
        list.addBook(book);
        list.addBook(book1);
        list.addBook(null);
        list.addBook(book);
        list.addBook(book2);
        student.setBookList(list);
        assertEquals(5, student.getBookList().size());
    }

    @Test
    public void getUsername() {
        assertEquals("ati", student.getUsername());
    }


    @Test
    public void setUsername() {
        student.setUsername(null);
        assertEquals(null, student.getUsername());
    }


    @Test
    public void getPassword() {
        assertEquals("joje", student.getPassword());
    }



    @Test
    public void setPassword() {
        student.setPassword(null);
        assertEquals(null, student.getPassword());
    }

    @Test
    public void getPhoneNumber() {
        assertEquals("91434065", student.getPhoneNumber());
    }


    @Test
    public void setPhoneNumber() {
        student.setPhoneNumber(null);
        assertEquals(null, student.getPhoneNumber());
    }

    @Test
    public void getEmail() {
        assertEquals("ati@via.dk", student.getEmail());
    }


    @Test
    public void setEmail() {
        student.setEmail(null);
        assertEquals(null, student.getEmail());
    }

    @Test
    public void getContactInfo() {
        System.out.println(student.getContactInfo());
    }

    /*@Test
    public void toString() {
    }*/

    @Test
    public void registerNewBook() {
        String t = "java", a = "me", y = "2020";
        student.registerNewBook(t, a, y);
        assertEquals(1, student.getBookList().size());
        System.out.println(student.getBookList());
    }

    @Test
    public void registerNewBook1() {
        Book b1 = new Book("test in java", "someone", "2015");
        student.registerNewBook(b1);
        assertEquals("ati", b1.getOwner().getUsername());
        System.out.println(student.getBookList());
    }

    @Test
    public void equals() {
        Student s1= student;

        assertEquals(true, s1.equals(student));
    }
}