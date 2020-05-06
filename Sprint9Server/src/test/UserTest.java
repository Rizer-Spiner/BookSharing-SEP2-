package test;

import RMI.RemoteClient;
import RMI.RemoteServer;
import model.domain.Student;
import model.domain.User;
import model.mediator.RmiServer;
import org.junit.Before;
import org.junit.Test;

import javax.jws.soap.SOAPBinding;
import java.net.MalformedURLException;
import java.rmi.RemoteException;
import java.sql.SQLException;

import static org.junit.Assert.*;

public class UserTest
{

   private User user;
   private RemoteServer server;


   @Before
   public void setUp()
         throws MalformedURLException, RemoteException, SQLException
   {
      this.server = new RmiServer();

      Student student = new Student("jnhvg", "hgvcfx", "hgf", "jhgcf");
      RemoteClient remoteClient = new RmiClient(null);

      this.user = new User(student, remoteClient);
   }
   @Test
   public void getAssociatedClient()
         throws MalformedURLException, RemoteException
   {
      Student student = new Student("jnhvg", "hgvcfx", "hgf", "jhgcf");
      RemoteClient remoteClient = new RmiClient(null);

      User user = new User(student, remoteClient);

      assertEquals(remoteClient, user.getAssociatedClient());

   }

   @Test
   public void setAssociatedClient()
         throws MalformedURLException, RemoteException
   {
      RemoteClient remoteClient = new RmiClient(null);

      user.setAssociatedClient(remoteClient);

      assertEquals(remoteClient,user.getAssociatedClient());
   }

   @Test
   public void getStudent()
   {
      Student student = new Student("jnhvg", "hgvcfx", "hgf", "jhgcf");

      assertEquals(student, user.getStudent());
   }

   @Test
   public void setStudent()
   {
      Student newStudent = new Student("njdjjj", "hjddhdhdhdh", "2121211", "4322@dasd.dd");
      user.setStudent(newStudent);

      assertEquals(newStudent, user.getStudent());
   }


   @Test
   public void equals1() throws MalformedURLException, RemoteException
   {
      Student student = new Student("jnhvg", "hgvcfx", "hgf", "jhgcf");
      RemoteClient remoteClient = new RmiClient(null);

      User userTest1 = new User(student, remoteClient);
      User userTest2 = new User(student, remoteClient);

      assertEquals(userTest1, userTest2);
   }


}