package test;

import RMI.RemoteClient;
import model.domain.*;
import utility.observer.listener.GeneralListener;
import utility.observer.subject.PropertyChangeProxy;

import java.net.MalformedURLException;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;

public class ModelManagerClient implements ModelClient
{
   private Student localUser;
   private RemoteClient client;
   private PropertyChangeProxy<Object,Object> propertyChangeProxy;
   private RequestList asOwner;
   private RequestList asBorrower;



   public ModelManagerClient() throws MalformedURLException, RemoteException
   {
      this.client = new RmiClient(this);
      this.propertyChangeProxy = new PropertyChangeProxy(this);

      this.asOwner = new RequestList();
      this.asBorrower = new RequestList();

   }

   @Override
   public boolean addListener(GeneralListener listener, String... propertyNames)
   {
      return propertyChangeProxy.addListener(listener);
   }

   @Override
   public boolean removeListener(GeneralListener listener,
         String... propertyNames)
   {
      return propertyChangeProxy.removeListener(listener);
   }

   @Override
   public void registerNewUser(Student newStudent, Book book)
         throws RemoteException, SQLException
   {
      book.setOwner(newStudent);
      client.registerNewUser(newStudent, book);
   }

   @Override
   public void logIn(Student student) throws RemoteException, SQLException
   {
      client.logIn(student);
   }

   @Override
   public void logOff() throws RemoteException
   {
      client.logOff(this.localUser);
      System.out.println();


   }

   @Override
   public void registerNewBook(Book book) throws RemoteException, SQLException
   {

      book.setOwner(localUser);

      client.registerNewBook(book);

   }

   @Override
   public void removeBook(Book book) throws RemoteException, SQLException
   {
      book.setOwner(localUser);
      client.removeBook(book);



   }

   @Override
   public void search(String category, String input) throws RemoteException
   {

      client.search(category, input);
   }

   @Override
   public void attemptBorrow(Book book) throws RemoteException
   {
      Request request = new Request(localUser, book);
      client.attemptBorrow(request);
   }

   @Override
   public void sendAnswerToRequest(Request request)
         throws RemoteException, SQLException
   {

      for (int i = 0; i < asOwner.size(); i++)
      {
         if (request.equals(asOwner.getRequests().get(i)))
         {
            asOwner.getRequests().remove(i);
            if(request.getStatus().equals("accepted"))
            {
               Book book = request.getBook();
               for(int j =0; j<localUser.getBookList().size(); j++)
               {
                  if(book.equals(localUser.getBookList().getBookList().get(j)))
                  {
                     localUser.getBookList().getBookList().get(j).setAvailable(false);
                  }
               }
            }
            
      }
         client.sendAnswerToRequest(request);
      }
   }


   @Override
   public void updateAvailability(Book book)
         throws RemoteException, SQLException
   {
      boolean available = book.isAvailable();
      for(int i = 0; i<localUser.getBookList().size(); i++)
      {
         if(book.equals(localUser.getBookList().getBookList().get(i)))
         {
            book.setAvailable(!(available));
            localUser.getBookList().getBookList().get(i).setAvailable(!(available));
            client.updateAvailability(book);
         }
      }

   }

   @Override
   public void receiveRegistrationMessage(String message, Student newStudent)
         throws RemoteException
   {
      propertyChangeProxy.firePropertyChange("REGISTRATION", message, " ");
      if (message.equals("REGISTRATION SUCCESS"))
      {

         localUser = newStudent;
      }

      System.out.println(localUser.getBookList().toString());
   }

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
               .firePropertyChange("LOG SUCCES", asOwner, asBorrower);


         System.out.println(localUser.getBookList().toString());


         if (asOwner.size()!=0)
         {

            for (int i = 0; i < asOwner.size(); i++)
            {
               System.out.println(asOwner.getRequests().get(i).toString());
            }
         }


         if (asBorrower.size()!=0)
         {

            for (int i = 0; i < asBorrower.size(); i++)
            {
               System.out.println(asBorrower.getRequests().get(i).toString() +" was "+ asBorrower.getRequests().get(i).getStatus());

              if( asBorrower.getRequests().get(i).getStatus().equals("ACCEPTED"))
              {

                 System.out.println();
                 System.out.println(localUser.getBookList().toString());
              }

            }

         }

      }
      else
      {
         propertyChangeProxy.firePropertyChange("LOG ERROR", null, null);

      }
   }

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


      for (int i = 0; i < books.size(); i++)
      {
         System.out.println(books.get(i).toString());
      }

   }

   @Override
   public void receiveSearchResult(ArrayList<Book> books) throws RemoteException
   {
      propertyChangeProxy.firePropertyChange("SEARCH RESULT", books, " ");
   }

   @Override
   public void receiveRequest(Request request) throws RemoteException
   {

      asOwner.add(request);
      propertyChangeProxy.firePropertyChange("REQUEST", null, asOwner);


      System.out.println(request.toString());
   }

   @Override
   public void receiveAswerToRequest(Request request) throws RemoteException
   {

      if(request.getStatus().equals("ACCEPTED"))
      {
         Book newBook = request.getBook();
         newBook.setAvailable(false);
         localUser.getBookList().addBook(newBook);
      }

      asBorrower.remove(request);
      propertyChangeProxy.firePropertyChange("ANSWER REQUEST", request, asBorrower);
   }

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

   @Override
   public void borrowedBookRemovedNotification(Book book) throws RemoteException
   {

      localUser.getBookList().removeBook(book);
      System.out.println(localUser.getBookList().toString());

      propertyChangeProxy.firePropertyChange("BORROW REMOVE", book, " ");
   }

   @Override
   public void receiveImposibleToRemove() throws RemoteException
   {

      propertyChangeProxy.firePropertyChange("REMOVE ERROR", " ", " ");

   }

}
