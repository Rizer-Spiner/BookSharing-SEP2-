package viewmodel;

import java.rmi.RemoteException;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import model.mediator.MVVMModelClient;

public class RegisterUserAndBookViewModel
{
   private StringProperty nameProperty;
   private StringProperty passwordProperty;
   private StringProperty emailProperty;
   private StringProperty phoneProperty;
   private StringProperty titleProperty;
   private StringProperty authorProperty;
   private StringProperty yearProperty;

   private MVVMModelClient model;

   public RegisterUserAndBookViewModel(MVVMModelClient model)
   {

      this.nameProperty = new SimpleStringProperty();
      this.passwordProperty = new SimpleStringProperty();
      this.emailProperty = new SimpleStringProperty();
      this.phoneProperty = new SimpleStringProperty();
      this.titleProperty = new SimpleStringProperty();
      this.authorProperty = new SimpleStringProperty();
      this.yearProperty = new SimpleStringProperty();
      this.model = model;

   }
   /**
    * Getter for nameProperty
    * @return nameProperty
    */
   public StringProperty getNameProperty()
   {
      return nameProperty;
   }
    /**
    * Getter for emailProperty
    * @return emailProperty
    */
   public StringProperty getEmailProperty()
   {
      return emailProperty;
   }
    /**
    * Getter for phoneProperty
    * @return phoneProperty
    */ 
   public StringProperty getPhoneProperty()
   {
      return phoneProperty;
   }
   /**
    * Getter for passwordProperty
    * @return passwordProperty
    */
   public StringProperty getPasswordProperty()
   {
      return passwordProperty;
   }
   /**
    * Getter for titleProperty
    * @return titleProperty
    */
   public StringProperty getTitleProperty()
   {
      return titleProperty;
   }
   /**
    * Getter for authorProperty
    * @return authorProperty
    */
   public StringProperty getAuthorProperty()
   {
      return authorProperty;
   }
   /**
    * Getter for yearProperty
    * @return yearProperty
    */
   public StringProperty getYearProperty()
   {
      return yearProperty;
   }
   
   /**
    * Gets property values.
    * Validates constraints.
    * If any of the fields is empty or null, throws IllegalArgumentException.
    * If year field is other then 4 digits , throws IllegalArgumentException.
    * If phone number field is other then 8 digits, throws IllegalArgumentException.
    * Creates new student.
    * Creates new book.
    * Sets book's owner to the student.
    * Registers student with the book.
    */
   public void register()
   {
      String n = nameProperty.getValue();
      if (n == null || n.isEmpty())
      {
         throw new IllegalArgumentException("Field cannot be empty");
      }
      String p = passwordProperty.getValue();
      if (p == null || p.isEmpty())
      {
         throw new IllegalArgumentException("Field cannot be empty");
      }
      String e = emailProperty.getValue();
      if (e == null || e.isEmpty())
      {
         throw new IllegalArgumentException("Field cannot be empty");
      }
      String ph = phoneProperty.getValue();
      if (ph == null || ph.isEmpty())
      {
         throw new IllegalArgumentException("Field cannot be empty");
      }
      if (ph.length() != 8)
      {
         throw new IllegalArgumentException("Invalid phone number");
      }
      try
      {
         Integer.parseInt(ph);
      }
      catch (NumberFormatException ex)
      {
         throw new IllegalArgumentException("Invalid phone number", ex);
      }
      String t = titleProperty.getValue();
      if (t == null || t.isEmpty())
      {
         throw new IllegalArgumentException("Field cannot be empty");
      }
      String a = authorProperty.getValue();
      if (a == null || a.isEmpty())
      {
         throw new IllegalArgumentException("Field cannot be empty");
      }
      String y = yearProperty.getValue();
      if (y == null || y.isEmpty())
      {
         throw new IllegalArgumentException("Field cannot be empty");
      }
      if (y.length() != 4)
      {
         throw new IllegalArgumentException("Invalid year");
      }
      try
      {
         Integer.parseInt(y);
      }
      catch (NumberFormatException ex)
      {
         throw new IllegalArgumentException("Invalid year", ex);
      }

      try
      {

         model.registerUser(n, p, e, ph, t, a, y);

      }
      catch (Exception e1)
      {

         e1.printStackTrace();
      }

   }
}
