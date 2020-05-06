package viewmodel;

import java.util.ArrayList;

import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import model.domain.Student;
import model.mediator.MVVMModelClient;
import utility.observer.event.ObserverEvent;
import utility.observer.listener.LocalListener;


public class ProfileViewModel implements LocalListener<Student,Student>
{
   
   private StringProperty usernameProperty;
   private StringProperty emailProperty;
   private StringProperty phoneProperty;
   private MVVMModelClient model;
   
   public ProfileViewModel(MVVMModelClient model)
   {
     
      this.model = model;
      this.model.getStudentModel().addListener(this,"localUser");
      this.usernameProperty = new SimpleStringProperty();
      this.emailProperty = new SimpleStringProperty();
      this.phoneProperty = new SimpleStringProperty();
      
     
   }
  
    /**
    * Getter for usernameProperty.
    * @return usernameProperty
    */
   public StringProperty getUsernameProperty()
   {
      return usernameProperty;
   }
    /**
    * Getter for emailProperty.
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
    * Sets username, email, phone number property to new values.
    */
   @Override
   public void propertyChange(ObserverEvent<Student, Student> event)
   {
      Platform.runLater(new Runnable() {

         @Override
         public void run()
         {
            usernameProperty.set(event.getValue2().getUsername());
            emailProperty.set(event.getValue2().getEmail());
            phoneProperty.set(event.getValue2().getPhoneNumber());
            
         }
         
      });
      
      
   }
   

}
