package viewmodel;


import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
public class RegisterUserAndBookViewModelTest
{
   private RegisterUserAndBookViewModel subject;
   
   @Before
   public void init()
   {
      subject = new RegisterUserAndBookViewModel(null);
     
   }
   @Test
   public void nullTitleThrowsException()
   {
      subject.getNameProperty().set("John");
      subject.getPasswordProperty().set("a");
      subject.getEmailProperty().set("a");
      subject.getPhoneProperty().set("11111111");
      
      subject.getTitleProperty().set(null);
      subject.getAuthorProperty().set("John");
      subject.getYearProperty().set("1999");
      
      try
      {
         subject.register();
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
      subject.getNameProperty().set("John");
      subject.getPasswordProperty().set("a");
      subject.getEmailProperty().set("a");
      subject.getPhoneProperty().set("11111111");
      
      subject.getTitleProperty().set("");
      subject.getAuthorProperty().set("John");
      subject.getYearProperty().set("1999");
      
      try
      {
         subject.register();
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
      subject.getNameProperty().set("John");
      subject.getPasswordProperty().set("a");
      subject.getEmailProperty().set("a");
      subject.getPhoneProperty().set("11111111");
      
      subject.getTitleProperty().set("thhh");
      subject.getAuthorProperty().set(null);
      subject.getYearProperty().set("1999");
      
      try
      {
         subject.register();
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
      subject.getNameProperty().set("John");
      subject.getPasswordProperty().set("a");
      subject.getEmailProperty().set("a");
      subject.getPhoneProperty().set("11111111");
      
      subject.getTitleProperty().set("thhh");
      subject.getAuthorProperty().set("");
      subject.getYearProperty().set("1999");
      
      try
      {
         subject.register();
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
      subject.getNameProperty().set("John");
      subject.getPasswordProperty().set("a");
      subject.getEmailProperty().set("a");
      subject.getPhoneProperty().set("11111111");
      
      subject.getTitleProperty().set("thhh");
      subject.getAuthorProperty().set("jfjf");
      subject.getYearProperty().set(null);
      
      try
      {
         subject.register();
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
      subject.getNameProperty().set("John");
      subject.getPasswordProperty().set("a");
      subject.getEmailProperty().set("a");
      subject.getPhoneProperty().set("11111111");
      
      subject.getTitleProperty().set("thhh");
      subject.getAuthorProperty().set("Q");
      subject.getYearProperty().set("");
      
      try
      {
         subject.register();
         assertEquals(false, true);
      }
      catch(IllegalArgumentException e)
      {
         assertEquals(true, true);
      }
      
   }
   @Test
   public void nonDigitPhoneThrowsException()
   {
      subject.getNameProperty().set("John");
      subject.getPasswordProperty().set("a");
      subject.getEmailProperty().set("a");
      subject.getPhoneProperty().set("1111111i");
      
      subject.getTitleProperty().set("thhh");
      subject.getAuthorProperty().set("uyff");
      subject.getYearProperty().set("1999");
      
      try
      {
         subject.register();
         assertEquals(false, true);
      }
      catch(IllegalArgumentException e)
      {
         assertEquals(true, true);
      }
      
   }
   @Test
   public void non8PhoneThrowsException()
   {
      subject.getNameProperty().set("John");
      subject.getPasswordProperty().set("a");
      subject.getEmailProperty().set("a");
      subject.getPhoneProperty().set("111111119");
      
      subject.getTitleProperty().set("thhh");
      subject.getAuthorProperty().set("hhhhh");
      subject.getYearProperty().set("1999");
      
      try
      {
         subject.register();
         assertEquals(false, true);
      }
      catch(IllegalArgumentException e)
      {
         assertEquals(true, true);
      }
      
   }
   
   


}
