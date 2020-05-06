package model.domain;

import RMI.RemoteClient;

import java.util.ArrayList;

/**
 * The UserList class represents the list of all Users
 *
 * @Author Group 4
 */
public class UserList
{
   private ArrayList<User> users;

   /**
    * constructor that creates an arrayList for users.
    */
   public UserList()
   {
      this.users = new ArrayList<User>();
   }

   /**
    * Adds a new User to the UserList
    *
    * @param student
    * @param client
    */
   public void addStudent(Student student, RemoteClient client)
   {
      if (student == null)
      {

      }
      else
      {
         User user = new User(student, client);
         users.add(user);
      }
   }

   /**
    * Removes a User from the UserList
    *
    * @param student
    * @param client
    */
   public void removeStudent(Student student, RemoteClient client)
   {
      if (users.size() < 1)
      {
         throw new IllegalStateException();
      }
      User userToRemove = new User(student, client);
      users.remove(userToRemove);
   }

   /**
    * Removes a User from the UserList identify using the Student object passed as argument
    *
    * @param student
    */
   public void removeStudent(Student student)
   {

      for (int i = 0; i < users.size(); i++)
      {

         if (student.equals(users.get(i).getStudent()))
         {

            users.remove(i);
         }
      }

   }

   /**
    * Returns an Arraylist of Students contained in the UserList
    * @return
    */
   public ArrayList<Student> getStudents()
   {
      ArrayList<Student> students = new ArrayList<>();
      for (int i = 0; i < users.size(); i++)
      {
         students.add(users.get(i).getStudent());
      }
      return students;
   }

   /**
    * Returns the size of the UserList
    * @return int
    */

   public int size()
   {
      return users.size();
   }

   /**
    * Returns an arrayList of Users containing all users from the UserList
    * @return ArrayList</User>
    */
   public ArrayList<User> getUsers()
   {
      return users;
   }
}
