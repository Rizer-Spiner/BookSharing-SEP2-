package viewmodel;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import model.mediator.MVVMModelClient;

public class LogInViewModel
{
   private StringProperty usernameProperty;
   private StringProperty passwordProperty;
   private StringProperty errorProperty;
   private MVVMModelClient model;
   
   public LogInViewModel(MVVMModelClient model)
   {
     this.model= model; 
     this.usernameProperty = new SimpleStringProperty();
     this.passwordProperty = new SimpleStringProperty();
     this.errorProperty = new SimpleStringProperty();
   }

   /**
    * Returns true if the logIn is a successful one.
    * Returns false otherwise.
    * @return boolean
    */

   public boolean validateLogin()
   {
      try
      {

     model.validateLogin(usernameProperty.get(), passwordProperty.get());
      errorProperty.set("");
      }
      catch (Exception e)
      {
         errorProperty.set(e.getMessage());
         return false;
      }
      return true;
   }

   /**
    * Getter for the username StringProperty
    * @return StringProperty
    */
   public StringProperty getUsernameProperty()
   {
      return usernameProperty;
   }

   /**
    * Getter for the password StringProperty
    * @return StringProperty
    */
   public StringProperty getPasswordProperty()
   {
      return passwordProperty;
   }

   /**
    * Getter for the error StringProperty
    * @return StringProperty
    */
   public StringProperty getErrorProperty()
   {
      return errorProperty;
   }
   

}
