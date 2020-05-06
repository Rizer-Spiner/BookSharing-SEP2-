package model.mediator;

import RMI.RemoteClient;
import model.domain.*;
import RMI.RemoteServer;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * RMI Server object implementation, implementing the RemoteServer interface
 * which extends the Remote interface. Contains logic used in the
 * finalization of the processes run on the Server side.
 *
 * @see java.rmi.Remote
 * @author Group 4
 * @version 1.5
 */

public class RmiServer implements RemoteServer
{
   private ModelServer model;
  

   private Student freddie;

   /**
    *
    * Constructor of yhe RMI Server object. In the constructor a registry is started that
    * would allow multiple Stub instances to connect to a single remote space. A ModelServer is
    * created.
    *
    *
    * @throws RemoteException
    * @throws MalformedURLException
    *
    *
    *
    * @see #startRegistery()
    * @see #startServer()
    *
    */
   public RmiServer()
         throws RemoteException, MalformedURLException
   {

      startRegistery();
      startServer();
      this.model = new ModelManagerServer();
    
   }

   /**
    * Method starting a local registry on the computer running the program.
    * In case a local registery is already created the method will not perfom anything.
    *
    * @throws RemoteException
    */
   public void startRegistery() throws RemoteException
   {
      try
      {
         Registry reg = LocateRegistry.createRegistry(1099);
       
      }
      catch (java.rmi.server.ExportException e)
      {
         
      }

   }

   /**
    *
    * Method exporting the RMI Server entity to the the created registery.
    * The object is binded through naming meaning only instances that will connect to the
    * "Library" stubs will be granted access to the remote functions.
    *
    * @throws RemoteException
    * @throws MalformedURLException
    *
    * @see Naming
    */


   public void startServer() throws RemoteException, MalformedURLException
   {
      UnicastRemoteObject.exportObject(this, 0);
      Naming.rebind("Library", this);
    
   }

   /**
    *  Registers a new user with the mandatory book as his/her first element of its
    *  bookList. Multiple conditions are checked with the ModelServer to see if the registration is
    *  possible or not. If the conditions are met the client will adopt the new User as localUser. In the contrary case
    *  no changes will be made.
    *
    * @param newStudent
    * @param book
    * @param rmiClient
    * @throws RemoteException
    *
    * @see model.mediator.ModelManagerServer#verifyIfUserIsUnique(Student)
    * @see ModelManagerServer#registerUser(Student, Book)
    * @see ModelManagerServer#registerNewOnlineUser(Student, RemoteClient)
    * @see ModelManagerServer#getBookListOfUser(Student)
    *
    */
   @Override
   public synchronized void registerNewUser(Student newStudent, Book book,
         RemoteClient rmiClient) throws RemoteException
   {

    

      if (model.verifyIfUserIsUnique(newStudent))
      {
        
         newStudent.registerNewBook(book);
         model.getLibrary().addToLibrary(book);
         model.registerNewOnlineUser(newStudent,
                rmiClient);
         try
         {
            model.registerUser(newStudent, book);
         }
         catch (SQLException e)
         {
            e.printStackTrace();
         }
         if(rmiClient!=null)
         {
            rmiClient.receiveRegistrationMessage("REGISTRATION SUCCESS",
                  newStudent);
            ArrayList<Book> list = model.getBookListOfUser(newStudent);
            rmiClient.receiveBookList(list);
         }
         if(freddie==null)
         {
            freddie = newStudent;
            model.removeOnlineUser(freddie);
         }
         else {
           
            atemptBorrow(new Request(freddie,book),null);

         }
      }
      else
      {
         if(rmiClient!=null)
         rmiClient.receiveRegistrationMessage("REGISTRATION ERROR", newStudent);

      }

    

   }

   /**
    *
    * Logs in one already existing user as an online user.
    * Certain conditions are checked to see if the login can be made or not.
    * In favorable case the client will receive the server's information
    * related to te user that he wants to be logged in as. If not the client receives
    * the error message and not changes will be done.
    *
    * @param student
    * @param rmiClient
    * @throws RemoteException
    *
    * @see ModelManagerServer#verifyIfUserExists(Student)
    * @see ModelManagerServer#verifyIfUserIsOnline(Student)
    * @see ModelManagerServer#getRequestListAsBorrower(Student)
    * @see ModelManagerServer#getRequestListasOwner(Student)
    */
   @Override
   public synchronized void logIn(Student student, RemoteClient rmiClient)
         throws RemoteException
   {

    

      if (model.verifyIfUserExists(student))
      {

         student = model.getStudent(student);
         if (!(model.verifyIfUserIsOnline(student)))
         {


            RequestList asOwner = model.getRequestListasOwner(student);

            RequestList asBorrower = model.getRequestListAsBorrower(student);


            rmiClient.receiveLogInResponse("LOG SUCCESS", student, asOwner,
                  asBorrower);

         }
         else
            rmiClient.receiveLogInResponse("LOG ERROR", student, null, null);
      }
      else
      {
         rmiClient.receiveLogInResponse("LOG ERROR", student, null, null);
      }

     
   }

   /**
    *
    * Removes one online user from the system. This will apply no changes to the user itself, the Student
    * representing that user.
    * A user that logs off will not be able to receive live notifications or access data modification
    * methods.
    *
    * @param localUser
    * @param rmiClient
    * @throws RemoteException
    *
    * @see ModelManagerServer#removeOnlineUser(Student)
    */
   @Override
   public synchronized void logOff(Student localUser, RemoteClient rmiClient)
         throws RemoteException
   {

      model.removeOnlineUser(localUser);
    
   }

   /**
    * Adds a new Book to a specific Student in the system. The student for with tha book needs
    * to be added is provided as the owner of the book already from the Client side.
    *
    *
    * @param book
    * @param rmiClient
    *
    * @see ModelManagerServer#registerNewBook(Book)
    */
   @Override
   public synchronized void registerNewBook(Book book, RemoteClient rmiClient)

   {
    
      try
      {
         model.registerNewBook(book);
      }
      catch (SQLException e)
      {
         e.printStackTrace();
      }

      ArrayList<Book> list = model.getBookListOfUser(book.getOwner());

      try
      {
         rmiClient.receiveBookList(list);
      }
      catch (RemoteException e)
      {
         e.printStackTrace();
      }
    

   }

   /**
    * Removes a book from a specific Student's BookList. The student is provided
    * as the owner of the Book. A book can be removed only is it is available.
    *
    * @param book
    * @param rmiClient
    * @throws RemoteException
    *
    * @see ModelManagerServer#removeBook(Book)
    * @see ModelManagerServer#getBookListOfUser(Student)
    */
   @Override
   public synchronized void removeBook(Book book, RemoteClient rmiClient)
         throws RemoteException
   {


      if (book.isAvailable())
      {
         boolean removed = false;
         try
         {
            removed = model.removeBook(book);
         }
         catch (SQLException e)
         {
            e.printStackTrace();
         }
         if (removed)
         {
            ArrayList<Book> list = model.getBookListOfUser(book.getOwner());
            rmiClient.receiveBookList(list);
         }
         else
         {
            rmiClient.receiveImposibleToRemove();
         }

      }
      else
      {
         rmiClient.receiveImposibleRequest();
      }
    

   }

   /**
    *
    * Searches for matching results with the Library' s data depending on the category chosen and input.
    * The category can be either "title" or "author".
    *
    * @param category
    * @param input
    * @param rmiClient
    * @throws RemoteException
    *
    * @see ModelManagerServer#getSearchResult(String, String)
    */
   @Override
   public synchronized void search(String category, String input , RemoteClient rmiClient)
         throws RemoteException
   {

      ArrayList<Book> searchResult = model.getSearchResult(category, input);

      rmiClient.receiveSearchResult(searchResult);

   }

   /**
    * Adds a Request object in the system's data if the conditions are met.
    * To request a book, the book must be available, not owned by the person
    * who is requesting it and the sytem must not have already pending Request
    * on the specific book.
    *
    * If the conditions are met and the owner of the book requested is online
    * it receives the request after its being added to the server's data.
    *
    *
    * @param request
    * @param rmiClient
    *
    * @see ModelManagerServer#bookHasRequest(Book)
    * @see ModelManagerServer#addRequest(Request)
    */
   @Override
   public synchronized void atemptBorrow(Request request,
         RemoteClient rmiClient)
   {

      if (request.getBook().isAvailable())
      {
         if (request.getBorrower().equals(request.getBook().getOwner()))
         {
            try
            {
               rmiClient.receiveImposibleRequest();
            }
            catch (RemoteException e)
            {
               e.printStackTrace();

            }
         }
         else
         {
            if (model.bookHasRequest(request.getBook()))
            {
               try
               {
                  rmiClient.receiveImposibleRequest();
               }
               catch (RemoteException e)
               {
                  e.printStackTrace();

               }
            }
            else
            {


               try
               {
                  model.addRequest(request);
               }
               catch (SQLException e)
               {
                  e.printStackTrace();
               }

               if (model.verifyIfUserIsOnline(request.getLender()))
               {

                  Student lender = request.getLender();
                  for (int i = 0; i < model.getOnlineUsers().size(); i++)
                  {
                     if (lender.equals(model.getOnlineUsers().getUsers().get(i)
                           .getStudent()))
                     {
                        RemoteClient lenderClient = (model.getAssociatedClient(lender));
                        try
                        {
                           lenderClient.receiveRequestList(model.getRequestListasOwner(lender));
                        }
                        catch (RemoteException e)
                        {
                           e.printStackTrace();

                        }

                     }
                  }
               }

            }
         }

      }
      else
      {
         try
         {
            rmiClient.receiveImposibleRequest();
         }
         catch (RemoteException e)
         {
            e.printStackTrace();
         }
      }
    

   }

   /**
    *
    * Handles the answer of one user to one specific request.
    * In the case of an accepted Request a borrow between the owner of the
    * requested book and the user requesting that book. In case of a denied request there will
    * be no changes to the system information.
    *
    * If the Student who requested the book is online it will receive the aswer to the request.
    * Else the system will save the modified request.
    *
    *
    * @param request
    * @param rmiClient
    * @throws RemoteException
    *
    * @see ModelManagerServer#verifyIfUserIsOnline(Student)
    * @see ModelManagerServer#getAssociatedClient(Student)
    * @see ModelManagerServer#changeRequestForAnswer(Request)
    * @see ModelManagerServer#borrow(Student, Book)
    */
   @Override
   public synchronized void receiveAnswerToRequest(Request request, RemoteClient rmiClient)
         throws RemoteException
   {


      Student borrower = request.getBorrower();

    
      if (model.verifyIfUserIsOnline(borrower))
      {

         model.getAssociatedClient(borrower).receiveAnswerToRequest(request);
       
         try
         {
            model.removeRequest(request);
         }
         catch (SQLException e)
         {
            e.printStackTrace();
         }
      }
      else
      {
         try
         {
            model.changeRequestForAnswer(request);
         }
         catch (SQLException e)
         {
            e.printStackTrace();
         }

      }

      if (request.getStatus().equals("accepted"))
      {
         model.borrow(request.getBorrower(), request.getBook());
         
      }
      else
      {

      }

      rmiClient.receiveRequestList(model.getRequestListasOwner(request.getLender()));

   }


   /**
    * Updates the availability of one Book when a borrow is completed.
    *
    *
    * @param book
    * @param rmiClient
    * @throws RemoteException
    *
    *
    * @see ModelManagerServer#updateAvailability(Book)
    * @see ModelManagerServer#getBookListOfUser(Student)
    */

   @Override
   public synchronized void updateAvailability(Book book, RemoteClient rmiClient)
         throws RemoteException
   {
    
      try
      {
         model.updateAvailability(book);
      }
      catch (SQLException e)
      {
         e.printStackTrace();
      }

      ArrayList<Book> list = model.getBookListOfUser(book.getOwner());
      rmiClient.receiveBookList(list);
    
   }

  

}