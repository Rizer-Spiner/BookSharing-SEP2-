package test;

import model.domain.Book;
import model.domain.Request;
import model.domain.Student;
import org.junit.Test;

import java.time.LocalDate;

import static org.junit.Assert.assertEquals;

public class RequestTest
{


   @Test
   public void getBook()
   {
      Student borrower = new Student("RizerSpiner", "youWish", "66666662", "6828@gd.hgf");
      Student lender = new Student("Class Royal", "gggggsa", "7625292", "6666@via.uk");

      Book book= new Book("Classic Danish Manners", "Soren Mansoor", "2003" , lender);

      LocalDate date = LocalDate.now();
      Request request = new Request(borrower, book,date);

      Book testBook = request.getBook();

      assertEquals(book, testBook);
   }

   @Test
   public void setBook()
   {
      Student borrower = new Student("RizerSpiner", "youWish", "66666662", "6828@gd.hgf");
      Student lender = new Student("Class Royal", "gggggsa", "7625292", "6666@via.uk");

      Book book= new Book("Classic Danish Manners", "Soren Mansoor", "2003" , lender);

     LocalDate date = LocalDate.now();
      Request request = new Request(borrower, book,date);

      Book book1 = new Book("Classic Danish Manners", "Soren Mansoor", "2003" , lender, false);
      request.setBook(book1);

      Book testBook = request.getBook();

      assertEquals(book1, testBook);
   }

   @Test
   public void getBorrower()
   {

      Student borrower = new Student("RizerSpiner", "youWish", "66666662", "6828@gd.hgf");
      Student lender = new Student("Class Royal", "gggggsa", "7625292", "6666@via.uk");

      Book book= new Book("Classic Danish Manners", "Soren Mansoor", "2003" , lender);

      LocalDate date = LocalDate.now();
      Request request = new Request(borrower, book,date);

      Student borrowerTest = request.getBorrower();

      assertEquals(borrowerTest, borrower);
   }

   @Test
   public void setBorrower()
   {

      Student borrower = new Student("RizerSpiner", "youWish", "66666662", "6828@gd.hgf");
      Student lender = new Student("Class Royal", "gggggsa", "7625292", "6666@via.uk");

      Book book= new Book("Classic Danish Manners", "Soren Mansoor", "2003" , lender);

     LocalDate date = LocalDate.now();
      Request request = new Request(borrower, book,date);

      Student studentToChange = new Student("Mainly Hands", "BodyCream122", "77773", "hahaabab@lab.ro");

      request.setBorrower(studentToChange);

      assertEquals(studentToChange, request.getBorrower());

   }

   @Test
   public void getLender()
   {
      Student borrower = new Student("RizerSpiner", "youWish", "66666662", "6828@gd.hgf");
      Student lender = new Student("Class Royal", "gggggsa", "7625292", "6666@via.uk");

      Book book= new Book("Classic Danish Manners", "Soren Mansoor", "2003" , lender);

     LocalDate date = LocalDate.now();
      Request request = new Request(borrower, book,date);

      Student lenderTest = request.getLender();

      assertEquals(lenderTest, lender);
   }

   @Test
   public void setLender()
   {
      Student borrower = new Student("RizerSpiner", "youWish", "66666662", "6828@gd.hgf");
      Student lender = new Student("Class Royal", "gggggsa", "7625292", "6666@via.uk");

      Book book= new Book("Classic Danish Manners", "Soren Mansoor", "2003" , lender);

     LocalDate date = LocalDate.now();
      Request request = new Request(borrower, book,date);

      Student studentToChange = new Student("Mainly Hands", "BodyCream122", "77773", "hahaabab@lab.ro");

      request.setLender(studentToChange);

      assertEquals(studentToChange, request.getLender());
   }

   @Test
   public void getStatus()
   {

      Student borrower = new Student("RizerSpiner", "youWish", "66666662", "6828@gd.hgf");
      Student lender = new Student("Class Royal", "gggggsa", "7625292", "6666@via.uk");

      Book book= new Book("Classic Danish Manners", "Soren Mansoor", "2003" , lender);

     LocalDate date = LocalDate.now();
      Request request = new Request(borrower, book,date);

      Student studentToChange = new Student("Mainly Hands", "BodyCream122", "77773", "hahaabab@lab.ro");

      String statusToChange = "ACCEPTED";

      String status = request.getStatus();

      assertEquals(status, "PROGRESS");
   }

   @Test
   public void setStatus()
   {
      Student borrower = new Student("RizerSpiner", "youWish", "66666662", "6828@gd.hgf");
      Student lender = new Student("Class Royal", "gggggsa", "7625292", "6666@via.uk");

      Book book= new Book("Classic Danish Manners", "Soren Mansoor", "2003" , lender);

     LocalDate date = LocalDate.now();
      Request request = new Request(borrower, book,date);

      Student studentToChange = new Student("Mainly Hands", "BodyCream122", "77773", "hahaabab@lab.ro");

      String statusToChange = "ACCEPTED";

      request.setStatus(statusToChange);

      assertEquals(request.getStatus(), "ACCEPTED");
   }

   @Test
   public void getDate()
   {
      Student borrower = new Student("RizerSpiner", "youWish", "66666662", "6828@gd.hgf");
      Student lender = new Student("Class Royal", "gggggsa", "7625292", "6666@via.uk");

      Book book= new Book("Classic Danish Manners", "Soren Mansoor", "2003" , lender);

     LocalDate date = LocalDate.now();
      Request request = new Request(borrower, book,date);

      Student studentToChange = new Student("Mainly Hands", "BodyCream122", "77773", "hahaabab@lab.ro");

      String statusToChange = "ACCEPTED";

      LocalDate dateTest = request.getDate();

      assertEquals(date, dateTest);
   }

   @Test
   public void setDate()
   {
      Student borrower = new Student("RizerSpiner", "youWish", "66666662", "6828@gd.hgf");
      Student lender = new Student("Class Royal", "gggggsa", "7625292", "6666@via.uk");

      Book book= new Book("Classic Danish Manners", "Soren Mansoor", "2003" , lender);

     LocalDate date = LocalDate.now();
      Request request = new Request(borrower, book,date);

      Student studentToChange = new Student("Mainly Hands", "BodyCream122", "77773", "hahaabab@lab.ro");

      String statusToChange = "ACCEPTED";

      LocalDate date1 = LocalDate.now();
      request.setDate(date1);

      assertEquals(request.getDate(), date1);
   }

   @Test
   public void equals1()
   {
      Student borrower = new Student("RizerSpiner", "youWish", "66666662", "6828@gd.hgf");
      Student lender = new Student("Class Royal", "gggggsa", "7625292", "6666@via.uk");

      Book book= new Book("Classic Danish Manners", "Soren Mansoor", "2003" , lender);

     LocalDate date = LocalDate.now();
      Request request = new Request(borrower, book,date);

      Student studentToChange = new Student("Mainly Hands", "BodyCream122", "77773", "hahaabab@lab.ro");

      String statusToChange = "ACCEPTED";

      Request request1 = new Request(borrower, book,date);

      assertEquals(request, request1);
   }

   @Test
   public void toString1()
   {
      Student borrower = new Student("RizerSpiner", "youWish", "66666662", "6828@gd.hgf");
      Student lender = new Student("Class Royal", "gggggsa", "7625292", "6666@via.uk");

      Book book= new Book("Classic Danish Manners", "Soren Mansoor", "2003" , lender);

     LocalDate date = LocalDate.now();
      Request request = new Request(borrower, book,date);

      Student studentToChange = new Student("Mainly Hands", "BodyCream122", "77773", "hahaabab@lab.ro");

      String statusToChange = "ACCEPTED";

      Request request1 = new Request(borrower, book,date);

      assertEquals(request.toString(), request1.toString());
   }
}