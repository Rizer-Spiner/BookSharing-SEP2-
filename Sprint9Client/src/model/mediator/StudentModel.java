package model.mediator;

import java.util.ArrayList;
import java.util.List;
import model.domain.Student;
import utility.observer.listener.GeneralListener;
import utility.observer.subject.LocalSubject;
import utility.observer.subject.PropertyChangeProxy;


public class StudentModel implements LocalSubject<Student,Student>
{
   private List<Student> studentList;
   private Student localUser;
   private PropertyChangeProxy<Student, Student> property;
   
   public StudentModel() 
   {
      this.studentList = new ArrayList<>();
      this.localUser = null;
      this.property = new PropertyChangeProxy<>(this);
   }
   /**
    * Returns student from student list with matching username.
    * @param username
    * @return
    */
   public Student getStudentByUsername(String username)
   {
      for(Student student: studentList)
      {
         if(student.getUsername().equals(username))
         {
            return student;
         }
      }
      return null; 
   }
 /**
  * Getter for localUser instance.
  * @return
  */
   public Student getLocalUser()
   {
      return localUser;
   }
   /**
    * Setter for localUser instance.
    * @param localUser
    */
   public void setLocalUser(Student localUser)
   {
      this.localUser = localUser;
      studentList.add(localUser);
      property.firePropertyChange("localUser", null, localUser);
   }
   /**
    *
    * Adds the listner passed as argument as listener to events emitted by the class
    *
    * @param listener
    * @param propertyNames
    * @return boolean
    */
   @Override
   public boolean addListener(GeneralListener<Student, Student> listener,
         String... propertyNames)
   {
     return property.addListener(listener, propertyNames);
   }
   /**
    *
    * Removes the listner passed as argument as listener to events emitted by the class
    *
    * @param listener
    * @param propertyNames
    * @return boolean
    */
   @Override
   public boolean removeListener(GeneralListener<Student, Student> listener,
         String... propertyNames)
   {
      return property.removeListener(listener, propertyNames);
   }
   

}
