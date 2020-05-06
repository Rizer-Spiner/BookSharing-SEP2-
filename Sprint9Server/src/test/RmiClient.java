package test;

import RMI.RemoteClient;
import model.domain.*;
import RMI.RemoteServer;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.SQLException;
import java.util.ArrayList;

public class RmiClient implements RemoteClient
{

    private RemoteServer server;
    private ModelClient model;



    public RmiClient(ModelClient model)
          throws RemoteException, MalformedURLException
    {

        try {
            UnicastRemoteObject.exportObject(this, 0);
            server = (RemoteServer) Naming.lookup("rmi://localhost:1099/Library");

        } catch (Exception e) {
            e.printStackTrace();
        }

        this.model = model;

    }

    @Override
    public void registerNewUser(Student newStudent, Book book)
          throws RemoteException, SQLException
    {
        server.registerNewUser(newStudent, book, this);

    }

    @Override
    public void logIn(Student student) throws RemoteException, SQLException
    {

        server.logIn(student, this);
    }

    @Override
    public void logOff(Student localUser) throws RemoteException
    {
        server.logOff(localUser, this);
    }

    @Override
    public void registerNewBook(Book book) throws RemoteException, SQLException
    {


        server.registerNewBook(book, this);

    }

    @Override
    public void removeBook(Book book) throws RemoteException, SQLException
    {
        server.removeBook(book, this);


    }

    @Override
    public void search(String category, String input) throws RemoteException
    {
        server.search(category,input, this);
    }


    @Override
    public void attemptBorrow(Request request)
    {
        try {
            server.atemptBorrow(request, this);
        } catch (RemoteException e) {
            e.printStackTrace();

        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

   
    @Override
    public void sendAnswerToRequest(Request request)
          throws RemoteException
    {
        server.receiveAnswerToRequest(request, this);
    }


    @Override
    public void receiveRegistrationMessage(String message, Student newStudent)
          throws RemoteException
    {
        model.receiveRegistrationMessage(message, newStudent);
    }

    @Override
    public void receiveLogInResponse(String message, Student student, RequestList asOwner, RequestList asBorrower)
          throws RemoteException
    {
        model.receiveLogInResponse(message,student, asOwner, asBorrower);
    }

    @Override
    public void receiveBookList(ArrayList<Book> books) throws RemoteException
    {
        model.receiveBookList(books);
    }

    @Override
    public void receiveSearchResult(ArrayList<Book> books) throws RemoteException

    {
        model.receiveSearchResult(books);
    }

    @Override
    public void receiveRequest(Request request) throws RemoteException
    {
        model.receiveRequest(request);
    }

    @Override
    public void receiveImposibleRequest() throws RemoteException
    {
        model.receiveImposibleRequest();
    }

    @Override
    public void receiveAnswerToRequest(Request request) throws RemoteException
    {
        model.receiveAswerToRequest(request);
    }

    @Override
    public void receiveWishNotification(Book book) throws RemoteException
    {
        model.receiveWishNotification(book);
    }

    @Override
    public void borrowedBookRemovedNotification(Book book)
          throws RemoteException
    {
        model.borrowedBookRemovedNotification(book);
    }

    @Override
    public void receiveImposibleToRemove() throws RemoteException
    {
        model.receiveImposibleToRemove();
    }

    @Override
    public void updateAvailability(Book book)
          throws RemoteException, SQLException
    {
        server.updateAvailability(book, this);
    }

   @Override
   public void receiveRequestList(RequestList requestList)
         throws RemoteException
   {

      
   }

}
