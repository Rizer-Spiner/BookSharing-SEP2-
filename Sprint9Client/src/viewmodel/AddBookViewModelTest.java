package viewmodel;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class AddBookViewModelTest
{
   private AddBookViewModel subject;
   
   @Before
   public void init()
   {
      subject = new AddBookViewModel(null);
     
   }
   @Test
   public void nullTitleThrowsException()
   {
      subject.getTitleProperty().set(null);
      subject.getAuthorProperty().set("John");
      subject.getYearProperty().set("1999");
      
      try
      {
         subject.addBook();
         assertEquals(false, true);
      }
      catch(IllegalArgumentException e)
      {
         assertEquals(true, true);
      }
      
      
   }
   @Test
   public void emptyTitleThrowsException()
   {
      subject.getTitleProperty().set("");
      subject.getAuthorProperty().set("John");
      subject.getYearProperty().set("1999");
      
      try
      {
         subject.addBook();
         assertEquals(false, true);
      }
      catch(IllegalArgumentException e)
      {
         assertEquals(true, true);
      }
      
   }
   @Test
   public void nullAuthorThrowsException()
   {
      subject.getTitleProperty().set("Bible");
      subject.getAuthorProperty().set(null);
      subject.getYearProperty().set("1");
      
      try
      {
         subject.addBook();
         assertEquals(false, true);
      }
      catch(IllegalArgumentException e)
      {
         assertEquals(true, true);
      }
      
   }
   @Test
   public void emptyAuthorThrowsException()
   {
      subject.getTitleProperty().set("Bible");
      subject.getAuthorProperty().set("");
      subject.getYearProperty().set("1");
      
      try
      {
         subject.addBook();
         assertEquals(false, true);
      }
      catch(IllegalArgumentException e)
      {
         assertEquals(true, true);
      }
      
   }
   @Test
   public void nullYearThrowsException()
   {
      subject.getTitleProperty().set("Bible");
      subject.getAuthorProperty().set("John");
      subject.getYearProperty().set(null);
      
      try
      {
         subject.addBook();
         assertEquals(false, true);
      }
      catch(IllegalArgumentException e)
      {
         assertEquals(true, true);
      }
      
   }
   @Test
   public void emptyYearThrowsException()
   {
      subject.getTitleProperty().set("Bible");
      subject.getAuthorProperty().set("John");
      subject.getYearProperty().set("");
      
      try
      {
         subject.addBook();
         assertEquals(false, true);
      }
      catch(IllegalArgumentException e)
      {
         assertEquals(true, true);
      }
      
   }
   @Test
   public void nonDigitYearThrowsException()
   {
      subject.getTitleProperty().set("Bible");
      subject.getAuthorProperty().set("John");
      subject.getYearProperty().set("EEEE");
      
      try
      {
         subject.addBook();
         assertEquals(false, true);
      }
      catch(IllegalArgumentException e)
      {
         assertEquals(true, true);
      }
      
   }
   @Test
   public void non4DigitYearThrowsException()
   {
      subject.getTitleProperty().set("Bible");
      subject.getAuthorProperty().set("John");
      subject.getYearProperty().set("55555");
      
      try
      {
         subject.addBook();
         assertEquals(false, true);
      }
      catch(IllegalArgumentException e)
      {
         assertEquals(true, true);
      }
      
   }
   
   


}
