package test;

import RMI.RemoteClient;
import model.domain.*;
import model.mediator.ModelManagerServer;
import model.mediator.ModelServer;
import RMI.RemoteServer;
import model.mediator.RmiServer;
import org.junit.Before;
import org.junit.Test;

import java.net.MalformedURLException;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class ModelManagerServerTest
{

   private ModelClient modelClient;
   private ModelServer modelServer;
   private RemoteServer server;

   @Before
   public void setUp() throws Exception
   {
      modelClient = new ModelManagerClient();
      modelServer = new ModelManagerServer();
      server = new RmiServer();

   }

   @Test
   public void registerNewOnlineUser()
         throws MalformedURLException, RemoteException
   {
      Student user1 = new Student("The Man", "123546", "55566677", "22333@via.dk");
      Book book1 = new Book("The Carnival", "J. T. Brooks", "4332");

      RemoteClient remoteClient1 = new RmiClient(modelClient);
      modelServer.registerNewOnlineUser(user1, remoteClient1);


      assertEquals(modelServer.getOnlineUsers().size(),1);

   }

   @Test
   public void verifyIfUserIsOnline()
         throws MalformedURLException, RemoteException
   {
      registerNewOnlineUser();
      Student user1 = new Student("The Man", "123546", "55566677", "22333@via.dk");
      boolean answer = modelServer.verifyIfUserIsOnline(user1);

      assertEquals(answer, true);

   }

   @Test
   public void registerUser() throws SQLException
   {
      Student user1 = new Student("My name", "Nothing", "6543900", "hhhh@ceee.sa");
      Book book = new Book("Hello world", "Java Mad Sceintist", "1456");
      user1.registerNewBook(book);
      modelServer.registerUser(user1, book);

      assertEquals(1, modelServer.getAllUsers().size());

   }

   @Test
   public void getRequestListasOwner() throws SQLException
   {

      Student user1 = new Student("My name", "Nothing", "6543900", "hhhh@ceee.sa");
      Student user2 = new Student("highMight", "PlusUltra", "jhgdfffdf", "88888888@via.dk");

      addRequest();
      RequestList requestListUser1 =modelServer.getRequestListasOwner(user1);
      RequestList requestListUser2 =modelServer.getRequestListasOwner(user2);

      assertEquals(requestListUser1.size(), 2);
      assertEquals(requestListUser2.size(), 1);
   }

   @Test
   public void getRequestListAsBorrower() throws SQLException
   {

      Student user1 = new Student("My name", "Nothing", "6543900", "hhhh@ceee.sa");
      Student user2 = new Student("highMight", "PlusUltra", "jhgdfffdf", "88888888@via.dk");

      addRequest();
      RequestList requestListUser1 =modelServer.getRequestListAsBorrower(user1);
      RequestList requestListUser2 =modelServer.getRequestListAsBorrower(user2);

      assertEquals(requestListUser1.size(), 1);
      assertEquals(requestListUser2.size(), 2);
   }

   @Test
   public void removeOnlineUser() throws MalformedURLException, RemoteException
   {
      registerNewOnlineUser();
      Student user2 = new Student("highMight", "PlusUltra", "jhgdfffdf", "88888888@via.dk");
      RemoteClient remoteClient =new RmiClient(modelClient);

      modelServer.registerNewOnlineUser(user2, remoteClient);

      modelServer.removeOnlineUser(user2);
      assertEquals(modelServer.getOnlineUsers().size(), 1);
   }

   @Test
   public void registerNewBook()
         throws MalformedURLException, RemoteException, SQLException
   {

      registerUser();
      Student user1 = new Student("My name", "Nothing", "6543900", "hhhh@ceee.sa");

      Book book = new Book("The bones of an invisible man", "Roxana Spiridon", "2019");
      book.setOwner(user1);
      Book book1 = new Book("Dare to dispair", "J. K. Rolling", "2007");
      book1.setOwner(user1);

      modelServer.registerNewBook(book);
      modelServer.registerNewBook(book1);

      assertEquals(3, modelServer.getBookListOfUser(user1).size());

   }

   @Test
   public void getBookListOfUser() throws SQLException
   {
      Student user1 = new Student("My name", "Nothing", "6543900", "hhhh@ceee.sa");

      Book book = new Book("The bones of an invisible man", "Roxana Spiridon", "2019");
      Book book1 = new Book("Dare to dispair", "J. K. Rolling", "2007");


      Book book3 = new Book("Hello world", "Java Mad Sceintist", "1456");
      registerUser();

      book.setOwner(user1);
      book1.setOwner(user1);
      book3.setOwner(user1);

      ArrayList<Book> list = new ArrayList<>();
      list.add(book3);
      list.add(book);
      list.add(book1);

      modelServer.registerNewBook(book);
      modelServer.registerNewBook(book1);


      assertEquals(list, modelServer.getBookListOfUser(user1));

   }

   @Test
   public void removeBook() throws SQLException
   {
      registerUser();
      Student user1 = new Student("My name", "Nothing", "6543900", "hhhh@ceee.sa");

      Book book = new Book("Could not find a better title", "The Wizard", "1");
      book.setOwner(user1);

      modelServer.registerNewBook(book);

      assertEquals(modelServer.getBookListOfUser(user1).size(), 2);

      modelServer.removeBook(book);

      assertEquals(modelServer.getBookListOfUser(user1).size(), 1);

   }

   @Test
   public void getSearchResult() throws SQLException
   {
      Student user1 = new Student("My name", "Nothing", "6543900", "hhhh@ceee.sa");

      Book book = new Book("Hey There", "The D. Kopper", "2000");
      Book book1 = new Book("Hello to you too", "The M. Silvver", "2000");
      Book book2 = new Book("Hey There", "Marcus Danis", "2017");

      book.setOwner(user1);
      book1.setOwner(user1);
      book2.setOwner(user1);

      modelServer.registerNewBook(book);
      modelServer.registerNewBook(book1);
      modelServer.registerNewBook(book2);

     ArrayList<Book> books =  modelServer.getSearchResult("TITLE", "Hey There");

     assertEquals(books.size(), 2);

     ArrayList<Book> books1 = modelServer.getSearchResult("AUTHOR", "The M. Silvver");

     assertEquals(books1.size(), 1);


   }

   @Test
   public void getOnlineUsers() throws MalformedURLException, RemoteException
   {
      registerNewOnlineUser();

      UserList list =modelServer.getOnlineUsers();

      assertEquals(list.size(), 1);

   }

   @Test
   public void addRequest() throws SQLException
   {
      Student user1 = new Student("My name", "Nothing", "6543900", "hhhh@ceee.sa");
      Student user2 = new Student("highMight", "PlusUltra", "jhgdfffdf", "88888888@via.dk");

      Book book1 = new Book("Hello m'a friend", "Gracy Roggers", "2789");
      book1.setOwner(user1);
      Book book2  = new Book("Gracy Field", "Right James", "1768");
      book2.setOwner(user1);
      Book book3 = new Book("Fully Dresses", "Higging Roth", "2233");
      book3.setOwner(user2);

      Request request1 = new Request(user1,book3);
      Request request2 = new Request(user2,book1);
      Request request3 = new Request(user2, book2);

      modelServer.addRequest(request1);
      modelServer.addRequest(request2);
      modelServer.addRequest(request3);

      assertEquals(modelServer.getRequestList().size(), 3);

   }

   @Test
   public void bookHasRequest() throws SQLException
   {
      addRequest();
      Student user1 = new Student("My name", "Nothing", "6543900", "hhhh@ceee.sa");
      Book book1 = new Book("Hello m'a friend", "Gracy Roggers", "2789");
      book1.setOwner(user1);

      boolean hasOrNot = modelServer.bookHasRequest(book1);

      assertEquals(true, hasOrNot);

   }



   @Test
   public void getAssociatedClient()
         throws MalformedURLException, RemoteException
   {
      Student user1 = new Student("The Man", "123546", "55566677", "22333@via.dk");
      Book book1 = new Book("The Carnival", "J. T. Brooks", "4332");

      RemoteClient remoteClient1 = new RmiClient(modelClient);
      modelServer.registerNewOnlineUser(user1, remoteClient1);

      RemoteClient remoteClient = modelServer.getAssociatedClient(user1);

      assertEquals(remoteClient, remoteClient1);


   }

   @Test
   public void changeRequestForAnswer() throws SQLException
   {
      Student user1 = new Student("My name", "Nothing", "6543900", "hhhh@ceee.sa");
      Student user2 = new Student("highMight", "PlusUltra", "jhgdfffdf", "88888888@via.dk");

      Book book1 = new Book("Hello m'a friend", "Gracy Roggers", "2789");
      book1.setOwner(user1);
      Book book2  = new Book("Gracy Field", "Right James", "1768");
      book2.setOwner(user1);
      Book book3 = new Book("Fully Dresses", "Higging Roth", "2233");
      book3.setOwner(user2);

      Request request1 = new Request(user1,book3);
      Request request2 = new Request(user2,book1);
      Request request3 = new Request(user2, book2);

      modelServer.addRequest(request1);
      modelServer.addRequest(request2);
      modelServer.addRequest(request3);

      request1.setStatus("ACCEPTED");
      modelServer.changeRequestForAnswer(request1);

      assertEquals(modelServer.getRequestList().size(), 3);

      boolean isItThere  = false;
      for(int i = 0; i<modelServer.getRequestList().size(); i++)
      {
         if(modelServer.getRequestList().getRequests().get(i).getStatus().equals("ACCEPTED"))
         {
            isItThere = true;
         }
      }
      assertEquals(true, isItThere);


   }


   @Test
   public void borrow() throws SQLException
   {
      Student user1 = new Student("My name", "Nothing", "6543900", "hhhh@ceee.sa");
      Student user2 = new Student("highMight", "PlusUltra", "jhgdfffdf", "88888888@via.dk");

      Book book1 = new Book("Hello m'a friend", "Gracy Roggers", "2789");
      user1.registerNewBook(book1);
      book1.setOwner(user1);
      Book book2  = new Book("Gracy Field", "Right James", "1768");
      user1.registerNewBook(book2);
      Book book3 = new Book("Fully Dresses", "Higging Roth", "2233");
      user2.registerNewBook(book3);

      modelServer.registerUser(user1, book1);
      modelServer.registerUser(user2,book3);

      modelServer.borrow(user2, book1);

      assertEquals(modelServer.getBookListOfUser(user1).size(), 2);
      assertEquals(modelServer.getBookListOfUser(user2).size(), 2);

      ArrayList<Book> list1 = modelServer.getBookListOfUser(user1);
      ArrayList<Book> list2 = modelServer.getBookListOfUser(user2);
      list2.toString();

      int counts = 0;

      for(int i = 0; i< list1.size(); i++)
      {
         if(list1.get(i).equals(book1))
         {
            if (list1.get(i).isAvailable()==false)
               counts++;
         }
      }

      for(int i = 0; i< list2.size(); i++)
      {
         if(list2.get(i).equals(book1))
         {
            if (list2.get(i).isAvailable()==false)
               counts++;
         }
      }

      assertEquals(counts, 2);
   }

   @Test
   public void verifyIfUserIsUnique() throws SQLException
   {
      registerUser();
      Student user1 = new Student("My name", "Nothing", "6543900", "hhhh@ceee.sa");

      boolean isthere = modelServer.verifyIfUserIsUnique(user1);

      assertEquals(isthere, false);

   }

   @Test
   public void verifyIfUserExists() throws SQLException
   {
      registerUser();
      Student user1 = new Student("My name", "Nothing", "6543900", "hhhh@ceee.sa");

      boolean isThere = modelServer.verifyIfUserExists(user1);

      assertEquals(isThere, true);

   }


   @Test
   public void getStudent() throws SQLException
   {
      registerUser();
      Student user1 = new Student("My name", "Nothing", "6543900", "hhhh@ceee.sa");

     Student studentReturnedByMethod = modelServer.getStudent(user1);
      assertEquals(user1, studentReturnedByMethod);

   }

   @Test
   public void removeRequest() throws SQLException
   {
      Student user1 = new Student("My name", "Nothing", "6543900", "hhhh@ceee.sa");
      Student user2 = new Student("highMight", "PlusUltra", "jhgdfffdf", "88888888@via.dk");

      Book book1 = new Book("Hello m'a friend", "Gracy Roggers", "2789");
      book1.setOwner(user1);
      Book book2  = new Book("Gracy Field", "Right James", "1768");
      book2.setOwner(user1);
      Book book3 = new Book("Fully Dresses", "Higging Roth", "2233");
      book3.setOwner(user2);

      Request request1 = new Request(user1,book3);
      Request request2 = new Request(user2,book1);
      Request request3 = new Request(user2, book2);

      modelServer.addRequest(request1);
      modelServer.addRequest(request2);
      modelServer.addRequest(request3);


      modelServer.removeRequest(request2);

      assertEquals(modelServer.getRequestList().size(), 2);

   }

   @Test
   public void updateAvailability() throws RemoteException, SQLException
   {

      Student user1 = new Student("My name", "Nothing", "6543900", "hhhh@ceee.sa");
      Student user2 = new Student("highMight", "PlusUltra", "jhgdfffdf", "88888888@via.dk");

      Book book1 = new Book("Hello m'a friend", "Gracy Roggers", "2789");
      user1.registerNewBook(book1);
      book1.setOwner(user1);
      Book book2  = new Book("Gracy Field", "Right James", "1768");
      user1.registerNewBook(book2);
      Book book3 = new Book("Fully Dresses", "Higging Roth", "2233");
      user2.registerNewBook(book3);

      modelServer.registerUser(user1, book1);
      modelServer.registerUser(user2, book3);

      modelServer.borrow(user2, book1);

      assertEquals(modelServer.getBookListOfUser(user1).size(), 2);
      assertEquals(modelServer.getBookListOfUser(user2).size(), 2);


      ArrayList<Book> list2 = modelServer.getBookListOfUser(user2);


      modelServer.updateAvailability(book1);
      ArrayList<Book> list1 = modelServer.getBookListOfUser(user1);

      assertEquals(modelServer.getBookListOfUser(user1).size(), 2);
      assertEquals(modelServer.getBookListOfUser(user2).size(), 1);

      list1.toString();

      int counts = 0;

      for(int i = 0; i< list1.size(); i++)
      {
         if(list1.get(i).equals(book1))
         {
            if (list1.get(i).isAvailable()==true)
               counts++;
         }
      }

    assertEquals(counts, 1);


   }

}