package model.mediator;

import RMI.RemoteClient;
import model.domain.*;
import utility.observer.listener.GeneralListener;
import utility.observer.subject.PropertyChangeProxy;

import java.net.MalformedURLException;
import java.rmi.RemoteException;
import java.util.ArrayList;

public class ModelManagerClient implements ModelClient
{
   private Student localUser;
   private RemoteClient client;
   private PropertyChangeProxy<Object, Object> propertyChangeProxy;
   private RequestList asOwner;
   private RequestList asBorrower;
   /**
    * Constructor initializes an RmiClient object, propertyChangeProxy object,requestlist recieved as an owner and requestlist sent as a borrower.
    * @throws MalformedURLException
    * @throws RemoteException
    */
   public ModelManagerClient() throws MalformedURLException, RemoteException
   {
      this.client = new RmiClient(this);
      this.propertyChangeProxy = new PropertyChangeProxy(this);

      this.asOwner = new RequestList();
      this.asBorrower = new RequestList();

   }
   /**
    * The method returns type boolean by calling addListener method in PropertyChangeProxy which adds listener
    * @param listener as type GeneralListener
    * @param propertyNames as type String
    * @return boolean
    */
   @Override
   public boolean addListener(GeneralListener listener, String... propertyNames)
   {
      return propertyChangeProxy.addListener(listener);
   }
   /**
    * The method returns type boolean by calling removeListener method in PropertyChangeProxy which removes listener
    * @param listener as type GeneralListener
    * @param propertyNames as type String
    * @return boolean
    */
   @Override
   public boolean removeListener(GeneralListener listener,
         String... propertyNames)
   {
      return propertyChangeProxy.removeListener(listener);
   }
   /**
    * The method assigns the newStudent of type student to a book of type Book
    * @param newStudent as type Student
    * @param book as type Book
    * @throws RemoteException
    */
   @Override
   public void registerNewUser(Student newStudent, Book book)
         throws RemoteException
   {
      book.setOwner(newStudent);
      client.registerNewUser(newStudent, book);
   }
   /**
    * The method logs in the student of type Student
    * @param student as Student
    * @throws RemoteException
    */
   @Override
   public void logIn(Student student) throws RemoteException
   {
      client.logIn(student);
   }
   /**
    * The method logs off the online user
    * @throws RemoteException
    */
   @Override
   public void logOff() throws RemoteException
   {
      client.logOff(this.localUser);

   }
   /**
    * The method registers a book of type Book to the online user
    * @param book as type Book
    * @throws RemoteException
    */
   @Override
   public void registerNewBook(Book book) throws RemoteException
   {

      book.setOwner(localUser);

      client.registerNewBook(book);

   }

   /**
    * The method removes a book of type Book from an online user
    * @param book
    * @throws RemoteException
    */
   @Override
   public void removeBook(Book book) throws RemoteException
   {
      book.setOwner(localUser);
      client.removeBook(book);

   }
   /**
    * The method searches a book by category and title of book
    * @param category as String
    * @param input as String
    * @throws RemoteException
    */
   @Override
   public void search(String category, String input) throws RemoteException
   {

      client.search(category, input);
   }
   /**
    * The method creates a request with a book of type Book with an online user(Borrower)
    * @param book as Book
    * @throws RemoteException
    */
   @Override
   public void attemptBorrow(Book book) throws RemoteException
   {
      Request request = new Request(localUser, book);
      client.attemptBorrow(request);
   }
   /**
    * The method sets the availability of the requested book to false as the borrow has taken place
    * @param request as Request
    * @throws RemoteException
    */
   @Override
   public void sendAnswerToRequest(Request request) throws RemoteException
   {

      for (int i = 0; i < asOwner.size(); i++)
      {
         if (request.equals(asOwner.getRequests().get(i)))
         {
            asOwner.getRequests().remove(i);
            if (request.getStatus().equals("accepted"))
            {
               Book book = request.getBook();
               for (int j = 0; j < localUser.getBookList().size(); j++)
               {
                  if (book.equals(localUser.getBookList().getBookList().get(j)))
                  {
                     localUser.getBookList().getBookList().get(j)
                           .setAvailable(false);
                  }
               }
            }

         }

      }

      client.sendAnswerToRequest(request);

   }
   /**
    * The method updates the availability of a book of type Book
    * @param book as type Book
    * @throws RemoteException
    */
   @Override
   public void updateAvailability(Book book) throws RemoteException
   {
      boolean available = book.isAvailable();
      int j = -1;
      for (int i = 0; i < localUser.getBookList().size(); i++)
      {
         if (book.equals(localUser.getBookList().getBookList().get(i)))
         {

            j = i;
            localUser.getBookList().getBookList().get(i)
                  .setAvailable(!(available));
         }
      }

      if (j != -1)
      {
         client.updateAvailability(
               localUser.getBookList().getBookList().get(j));
      }
   }

   /**
    * The method fires a property change method when a user is registered
    * @param message as String
    * @param newStudent as Student
    * @throws RemoteException
    */
   @Override
   public void receiveRegistrationMessage(String message, Student newStudent)
   {
      propertyChangeProxy.firePropertyChange("REGISTRATION", message, " ");
      if (message.equals("REGISTRATION SUCCESS"))
      {

         localUser = newStudent;
      }

   }
   /**
    * The method fires a property change method when a user has logged in successfully
    * @param message as String
    * @param student as Student
    * @param asOwner as RequestList
    * @param asBorrower as RequestList
    * @throws RemoteException
    */
   @Override
   public void receiveLogInResponse(String message, Student student,
         RequestList asOwner, RequestList asBorrower) throws RemoteException
   {

      if (message.equals("LOG SUCCESS"))
      {
        
         this.localUser = student;
         this.asOwner = asOwner;
         this.asBorrower = asBorrower;

         propertyChangeProxy
               .firePropertyChange("REQUEST UPDATE", null, asOwner);
         propertyChangeProxy.firePropertyChange("LOCAL USER", null, student);
         propertyChangeProxy
               .firePropertyChange("BOOKLIST", null, student.getBookList());

      }
      else
      {
         propertyChangeProxy.firePropertyChange("LOG ERROR", null, null);

      }
   }
   /**
    * The method sets a user's book list
    * @param books as ArrayList<Book>
    * @throws RemoteException
    */
   @Override
   public void receiveBookList(ArrayList<Book> books) throws RemoteException
   {
      BookList list = new BookList();
      for (int i = 0; i < books.size(); i++)
      {
         list.addBook(books.get(i));
      }
      localUser.setBookList(list);

      propertyChangeProxy.firePropertyChange("BOOKLIST", null, list);

   }

   /**
    * The method fires a property change method when a list of books are searched for
    * @param books as Book
    * @throws RemoteException
    */
   @Override
   public void receiveSearchResult(ArrayList<Book> books) throws RemoteException
   {
      propertyChangeProxy.firePropertyChange("SEARCH RESULT", books, " ");
   }
   /**
    * The method adds a request for a book owned by the owner to the respective request list
    * @param request as Request
    * @throws RemoteException
    */
   @Override
   public void receiveRequest(Request request) throws RemoteException
   {

      asOwner.add(request);
      propertyChangeProxy.firePropertyChange("REQUEST", null, asOwner);

   }

   /**
    * The method updated the answer of a request to the borrower's request list and updated both request list and book list
    * @param request as Request
    * @throws RemoteException
    */
   @Override
   public void receiveAswerToRequest(Request request) throws RemoteException
   {
      if (request.getStatus().equals("accepted"))
      {
         Book newBook = request.getBook();
         newBook.setAvailable(false);
         localUser.getBookList().addBook(newBook);
      }
      asBorrower.remove(request);
      propertyChangeProxy
            .firePropertyChange("ANSWER REQUEST", request, asBorrower);
   }
   /**
    * The method fires a property change method if multiple requests are placed on a book
    * @throws RemoteException
    */
   @Override
   public void receiveImposibleRequest() throws RemoteException
   {
      propertyChangeProxy.firePropertyChange("IMPOSIBLE REQUEST", " ", " ");
   }

   @Override
   public void receiveWishNotification(Book book) throws RemoteException
   {
      propertyChangeProxy.firePropertyChange("WISH", book, " ");
   }
   /**
    * The method removes a borrowed book from the owner's book list
    * @param book as Book
    * @throws RemoteException
    */
   @Override
   public void borrowedBookRemovedNotification(Book book) throws RemoteException
   {

      localUser.getBookList().removeBook(book);

      propertyChangeProxy.firePropertyChange("BORROW REMOVE", book, " ");
   }

   /**
    * Fires an event specific for the situation when the user wants to remove its last book
    * @throws RemoteException
    */
   @Override
   public void receiveImposibleToRemove() throws RemoteException
   {

      propertyChangeProxy.firePropertyChange("REMOVE ERROR", " ", " ");

   }
   /**
    * The method shows the updated request list of a user
    * @param requestList
    */
   @Override
   public void receiveRequestList(RequestList requestList)
   {

      propertyChangeProxy
            .firePropertyChange("REQUEST UPDATE", null, requestList);
   }

}
