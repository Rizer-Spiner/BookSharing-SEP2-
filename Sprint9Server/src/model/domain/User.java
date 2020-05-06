package model.domain;

import RMI.RemoteClient;

import javax.jws.soap.SOAPBinding;

/**
 * The User class representing the remote client  having
 * two instance variable one type Student the other one RemoteClient
 * the remoteClient is marked as volatile which means
 * it is stored in the main memory and it is thread safe.
 * @Author Group 4
 */
public class User
{
   private Student student;
   private volatile RemoteClient associatedClient;

   /**
    * the constructor initializing the instance variables
    * @param student
    * @param client
    */
   public User(Student student, RemoteClient client)
   {

      this.associatedClient = client;
      this.student = student;
   }

   /**
    * Returns the Proxy stub remote object depicted as a RemoteClient interface.
    *
    * @return associatedClient as the remote client
    */
   public RemoteClient getAssociatedClient()
   {
      return associatedClient;
   }

   /**
    * Sets the Proxy stub remote object with the one passed as argument.
    *
    * @param associatedClient
    */
   public void setAssociatedClient(RemoteClient associatedClient)
   {
      this.associatedClient = associatedClient;
   }

   /**
    * Returns the Student object that is part of the User.
    * @return student
    */
   public Student getStudent()
   {
      return student;
   }

   /**
    * Sets the Student object that is part of the User.
    * @param student
    */
   public void setStudent(Student student)
   {
      this.student = student;
   }

   /**
    * Returns a string representation of the User
    * @return String
    */
   @Override
   public String toString()
   {
      return student.toString();
   }

   /**
    * Returns true if the User student object and RemoteClient are equal to the
    * Object's passed as argument.
    *
    * @param obj
    * @return boolean
    */
   @Override
   public boolean equals(Object obj)
   {
      if(!(obj instanceof User))return false;

      User other = (User) obj;

      return other.getAssociatedClient().equals(this.associatedClient)&&this.getStudent().equals(other.student);
   }

}
