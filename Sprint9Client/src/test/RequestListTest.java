package test;

import model.domain.Book;
import model.domain.Request;
import model.domain.RequestList;
import model.domain.Student;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;

import static org.junit.Assert.assertEquals;

public class RequestListTest
{


private RequestList requestList;
@Before
public void setUp()
{
   this.requestList = new RequestList();
}
   @Test (expected = IllegalStateException.class)
   public void add()
   {
      Student borrower = new Student("RizerSpiner", "youWish", "66666662",
            "6828@gd.hgf");
      Student lender = new Student("Class Royal", "gggggsa", "7625292",
            "6666@via.uk");

      Book book = new Book("Classic Danish Manners", "Soren Mansoor", "2003",
            lender);

      LocalDate date = LocalDate.now();
      Request request = new Request(borrower, book, date);

      Student borrower2 = new Student("Ricky Martin", "hgvf", "ugf", "jhgcfs");
      Student lender2 = new Student("kjn", "khgf", "hvfas", "gvfsdfs");

      Book book1 = new Book("jidso", "jhsg", "iueeeeeeeeeee", lender2);

      Request request1 = new Request(borrower, book1);

      Request request2 = null;

      requestList.add(request);
      requestList.add(request1);
      requestList.add(request2);

      assertEquals(requestList.size(), 2);


   }

   @Test (expected = IllegalStateException.class)
   public void remove()
   {
      Student borrower = new Student("RizerSpiner", "youWish", "66666662",
            "6828@gd.hgf");
      Student lender = new Student("Class Royal", "gggggsa", "7625292",
            "6666@via.uk");

      Book book = new Book("Classic Danish Manners", "Soren Mansoor", "2003",
            lender);

      LocalDate date = LocalDate.now();
      Request request = new Request(borrower, book, date);

      Student borrower2 = new Student("Ricky Martin", "hgvf", "ugf", "jhgcfs");
      Student lender2 = new Student("kjn", "khgf", "hvfas", "gvfsdfs");

      Book book1 = new Book("jidso", "jhsg", "iueeeeeeeeeee", lender2);

      Request request1 = new Request(borrower, book1);

      Request request2 = null;

      requestList.add(request);
      requestList.add(request1);
      requestList.add(request2);

      requestList.remove(request);

      assertEquals(requestList.size(), 1);
   }

   @Test (expected = IllegalStateException.class)
   public void remove1()
   {
      Student borrower = new Student("RizerSpiner", "youWish", "66666662",
            "6828@gd.hgf");
      Student lender = new Student("Class Royal", "gggggsa", "7625292",
            "6666@via.uk");

      Book book = new Book("Classic Danish Manners", "Soren Mansoor", "2003",
            lender);

      LocalDate date = LocalDate.now();
      Request request = new Request(borrower, book, date);

      Student borrower2 = new Student("Ricky Martin", "hgvf", "ugf", "jhgcfs");
      Student lender2 = new Student("kjn", "khgf", "hvfas", "gvfsdfs");

      Book book1 = new Book("jidso", "jhsg", "iueeeeeeeeeee", lender2);

      Request request1 = new Request(borrower, book1);

      Request request2 = null;

      requestList.add(request);
      requestList.add(request1);
      requestList.add(request2);

      requestList.remove(0);

      assertEquals(requestList.size(), 1);

   }

   @Test (expected = IllegalStateException.class)
   public void size()
   {
      add();
   }
}