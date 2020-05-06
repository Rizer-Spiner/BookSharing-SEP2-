package test;

import RMI.RemoteClient;
import RMI.RemoteServer;
import model.domain.Student;
import model.domain.User;
import model.domain.UserList;
import model.mediator.RmiServer;
import org.junit.Before;
import org.junit.Test;

import java.net.MalformedURLException;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class UserListTest
{
   private UserList userList;
   private RemoteServer server;

   @Before
   public void setUp()
         throws MalformedURLException, RemoteException, SQLException
   {
      this.userList = new UserList();
      this.server = new RmiServer();
   }

   @Test
   public void addStudent() throws MalformedURLException, RemoteException
   {
      Student student = new Student("hbhas", "bshadsa", "asnjnda",
            "2sdaaaaaaaaaa");
      RemoteClient remoteClient = new RmiClient(null);

      Student student1 = new Student("dsaaaaaaaaaaaaa", "sdddddddd",
            "12122222222", "dasd");
      RemoteClient remoteClient1 = new RmiClient(null);

      userList.addStudent(student, remoteClient);
      userList.addStudent(student1, remoteClient1);

      assertEquals(userList.size(), 2);
   }

   @Test
   public void removeStudent() throws MalformedURLException, RemoteException
   {
      Student student = new Student("hbhas", "bshadsa", "asnjnda",
            "2sdaaaaaaaaaa");
      RemoteClient remoteClient = new RmiClient(null);

      Student student1 = new Student("dsaaaaaaaaaaaaa", "sdddddddd",
            "12122222222", "dasd");
      RemoteClient remoteClient1 = new RmiClient(null);

      userList.addStudent(student, remoteClient);
      userList.addStudent(student1, remoteClient1);

      assertEquals(userList.size(), 2);

      userList.removeStudent(student);

      assertEquals(userList.size(), 1);
   }

   @Test
   public void removeStudent1() throws MalformedURLException, RemoteException
   {
      Student student = new Student("hbhas", "bshadsa", "asnjnda",
            "2sdaaaaaaaaaa");
      RemoteClient remoteClient = new RmiClient(null);

      Student student1 = new Student("dsaaaaaaaaaaaaa", "sdddddddd",
            "12122222222", "dasd");
      RemoteClient remoteClient1 = new RmiClient(null);

      userList.addStudent(student, remoteClient);
      userList.addStudent(student1, remoteClient1);

      assertEquals(userList.size(), 2);

      userList.removeStudent(student, remoteClient);
      assertEquals(userList.size(), 1);
   }

   @Test
   public void getStudents() throws MalformedURLException, RemoteException
   {
      ArrayList<Student> list = new ArrayList<>();
      Student student = new Student("hbhas", "bshadsa", "asnjnda",
            "2sdaaaaaaaaaa");
      RemoteClient remoteClient = new RmiClient(null);
      list.add(student);

      Student student1 = new Student("dsaaaaaaaaaaaaa", "sdddddddd",
            "12122222222", "dasd");
      RemoteClient remoteClient1 = new RmiClient(null);
      list.add(student1);

      userList.addStudent(student, remoteClient);
      userList.addStudent(student1, remoteClient1);

      assertEquals(userList.size(), 2);


     ArrayList<Student> testList = userList.getStudents();

     assertEquals(testList, list);
   }

   @Test
   public void size() throws MalformedURLException, RemoteException
   {
      addStudent();
   }

   @Test
   public void getUsers() throws MalformedURLException, RemoteException
   {
      ArrayList<User>  list = new ArrayList<>();
      Student student = new Student("hbhas", "bshadsa", "asnjnda",
            "2sdaaaaaaaaaa");
      RemoteClient remoteClient = new RmiClient(null);

      User user = new User(student, remoteClient);
      list.add(user);

      Student student1 = new Student("dsaaaaaaaaaaaaa", "sdddddddd",
            "12122222222", "dasd");
      RemoteClient remoteClient1 = new RmiClient(null);

      User user1 = new User(student1, remoteClient1);
      list.add(user1);

      userList.addStudent(student, remoteClient);
      userList.addStudent(student1, remoteClient1);

      assertEquals(userList.size(), 2);

      ArrayList<User>  test = userList.getUsers();

      assertEquals(test, list);
   }
}