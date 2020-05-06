package test;

import model.domain.Book;
import model.domain.Library;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

public class LibraryTest
{

   private Library library;

   @org.junit.Before
   public void setUp() throws Exception
   {
      library = new Library();
   }

   @Test
   public void addToLibrary()
   {

      Book b1 = new Book("hi", "uvgfa", "2010");
      Book b2 = new Book("hola", "mgfgv", "2018");
      Book b3 = new Book("hihi", "hfgho", "2015");
      Book b4 = new Book("bye", "hjghg", "2000");

      library.addToLibrary(b1);

      library.addToLibrary(b2);
      library.addToLibrary(b3);
      library.addToLibrary(b4);

      assertEquals(4, library.size());

   }

   @Test
   public void removeFromLibrary()
   {
      Library library = new Library();
      Book b1 = new Book("hi", "uvgfa", "2010");
      Book b2 = new Book("hola", "mgfgv", "2018");
      Book b3 = new Book("hihi", "hfgho", "2015");
      Book b4 = new Book("bye", "hjghg", "2000");

      library.addToLibrary(b1);

      library.addToLibrary(b2);
      library.addToLibrary(b3);
      library.addToLibrary(b4);
      library.removeFromLibrary(b2);
      library.removeFromLibrary(b4);
      assertEquals(2, library.size());

   }

   @Test
   public void removeFromLibrary1()
   {
      Book b1 = new Book("hi", "uvgfa", "2010");
      Book b2 = new Book("hola", "mgfgv", "2018");
      Book b3 = new Book("hihi", "hfgho", "2015");
      Book b4 = new Book("bye", "hjghg", "2000");

      library.addToLibrary(b1);

      library.addToLibrary(b2);
      library.addToLibrary(b3);
      library.addToLibrary(b4);

      library.removeFromLibrary(b4);
      assertEquals(3, library.size());
   }

   @Test
   public void size()
   {
      Book b1 = new Book("hi", "uvgfa", "2010");
      Book b2 = new Book("hola", "mgfgv", "2018");
      Book b3 = new Book("hihi", "hfgho", "2015");
      Book b4 = new Book("bye", "hjghg", "2000");

      library.addToLibrary(b1);

      library.addToLibrary(b2);
      library.addToLibrary(b3);
      library.addToLibrary(b4);

      assertEquals(4, library.size());

   }

   @Test
   public void get()
   {
      Book b1 = new Book("hi", "uvgfa", "2010");
      Book b2 = new Book("hola", "mgfgv", "2018");
      Book b3 = new Book("hihi", "hfgho", "2015");
      Book b4 = new Book("bye", "hjghg", "2000");

      library.addToLibrary(b1);

      library.addToLibrary(b2);
      library.addToLibrary(b3);
      library.addToLibrary(b4);

      assertEquals(4, library.size());

   }

   @Test
   public void bookStatus()
   {
      Book b1 = new Book("hi", "uvgfa", "2010");
      Book b2 = new Book("hola", "mgfgv", "2018");
      Book b3 = new Book("hihi", "hfgho", "2015");
      Book b4 = new Book("bye", "hjghg", "2000");

      library.addToLibrary(b1);

      library.addToLibrary(b2);
      library.addToLibrary(b3);
      library.addToLibrary(b4);

      assertEquals(true, library.get(2).isAvailable());
   }
}