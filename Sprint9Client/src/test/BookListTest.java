package test;

import model.domain.Book;
import model.domain.BookList;
import model.domain.Student;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class BookListTest
{
    private BookList list;

    @Before
    public void setUp() throws Exception {
        list = new BookList();
    }

    @Test
    public void addBook() {
        Student owner = new Student("everyone", "all", "22552255", "alll@via.dk");
        Book book = new Book("booksharing", "group4", "2019", owner);
        Book book1 = new Book("java", "hbv", "2019", owner);
        Book book2 = new Book("booksharing", "kol", "2019", owner);
        list.addBook(book);
        list.addBook(book1);
        list.addBook(book);
        list.addBook(null);
        list.addBook(book2);
        assertEquals(4, list.size());

    }


    @Test
    public void removeBook() {
        Student owner = new Student("everyone", "all", "22552255", "alll@via.dk");
        Book book = new Book("booksharing", "group4", "2019", owner);
        Book book1 = new Book("java", "hbv", "2019", owner);
        Book book2 = new Book("booksharing", "kol", "2019", owner);
        list.addBook(book);
        list.addBook(book1);
        list.addBook(null);
       list.addBook(null);
       list.addBook(book2);
       assertEquals(0, list.size());
        list.removeBook(book1);
    list.removeBookByIndex(2);
        assertEquals(4,list.size());
    }


    @Test
    public void removeBookByIndex() {
        Student owner = new Student("everyone", "all", "22552255", "alll@via.dk");
        Book book = new Book("booksharing", "group4", "2019", owner);
        Book book1 = new Book("java", "hbv", "2019", owner);
        Book book2 = new Book("booksharing", "kol", "2019", owner);
        list.addBook(book);
        list.addBook(book1);
        list.addBook(null);
        list.addBook(book2);
        list.removeBookByIndex(0);
        assertEquals(book1, list.getBookList().get(0));


    }

    @Test
    public void viewList() {
        Student owner = new Student("everyone", "all", "22552255", "alll@via.dk");
        Book book = new Book("booksharing", "group4", "2019", owner);
        Book book1 = new Book("java", "hbv", "2019", owner);
        Book book2 = new Book("booksharing", "kol", "2019", owner);
        list.addBook(book);
        list.addBook(book1);
        list.addBook(null);
        list.addBook(book2);

        System.out.println(list.viewList());

    }


    @Test
    public void getBookList() {
        Student owner = new Student("everyone", "all", "22552255", "alll@via.dk");
        Book book = new Book("booksharing", "group4", "2019", owner);
        Book book1 = new Book("java", "hbv", "2019", owner);
        Book book2 = new Book("booksharing", "kol", "2019", owner);
        list.addBook(book);
        list.addBook(book1);
        list.addBook(null);
        list.addBook(book2);
        System.out.println(list.getBookList());
    }


    @Test
    public void size() {
        Student owner = new Student("everyone", "all", "22552255", "alll@via.dk");
        Book book = new Book("booksharing", "group4", "2019", owner);
        Book book1 = new Book("java", "hbv", "2019", owner);
        Book book2 = new Book("booksharing", "kol", "2019", owner);
        list.addBook(book);
        list.addBook(null);
        list.addBook(book1);
        list.addBook(null);
        list.addBook(book2);

        assertEquals(3, list.size());
    }

   /* @Test
    public String toString() {
return null;
    }*/
}