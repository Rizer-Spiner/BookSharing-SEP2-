package model.mediator;

import RMI.RemoteClient;
import RMI.RemoteServer;
import model.domain.*;

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


    /**
     * The constructor initializes the modelClient object and RemoteServer object
     * @param model as ModelClient
     * @throws RemoteException
     * @throws MalformedURLException
     */
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
    /**
     * The method registers a user with a book of type Book
     * @param newStudent as type Student
     * @param book as type Book
     * @throws RemoteException
     */
    @Override
    public void registerNewUser(Student newStudent, Book book)
          throws RemoteException
    {
        server.registerNewUser(newStudent, book, this);

    }
    /**
     * The method logs in the user
     * @param student as type Student
     * @throws RemoteException
     */
    @Override
    public void logIn(Student student) throws RemoteException
    {

        server.logIn(student, this);

    }
    /**
     * The method logs off a user of type Student
     * @param localUser as type Student
     * @throws RemoteException
     */
    @Override
    public void logOff(Student localUser) throws RemoteException
    {
        server.logOff(localUser, this);
    }
    /**
     * The method registers a book to an online user
     * @param book as type Book
     * @throws RemoteException
     */
    @Override
    public void registerNewBook(Book book) throws RemoteException
    {


        server.registerNewBook(book, this);

    }
    /**
     * The method removes book from online user
     * @param book as type Book
     * @throws RemoteException
     */
    @Override
    public void removeBook(Book book) throws RemoteException
    {
        server.removeBook(book, this);


    }
    /**
     * The book is searched by category and title
     * @param category of type String
     * @param input of type String
     * @throws RemoteException
     */
    @Override
    public void search(String category, String input) throws RemoteException
    {
        server.search(category,input, this);
    }
    /**
     * The method sends a borrow request with the current user
     * @param request as type Request
     */
    @Override
    public void attemptBorrow(Request request) 
    {
        try {
            server.atemptBorrow(request, this);
        } catch (RemoteException e) {
            e.printStackTrace();

        }
    }
    /**
     * The method sends an answer to request
     * @param request as type Request
     * @throws RemoteException
     */
    @Override
    public void sendAnswerToRequest(Request request) throws RemoteException
    {

        server.receiveAnswerToRequest(request, this);
    }

    /**
     * The method sends registraion message when user is registered
     * @param message as type String
     * @param newStudent as type Student
     * @throws RemoteException
     */
    @Override
    public void receiveRegistrationMessage(String message, Student newStudent)
          throws RemoteException
    {
        model.receiveRegistrationMessage(message, newStudent);
    }
    /**
     * The method sends a login response with a message to the user
     * @param message as type String
     * @param student as type Student
     * @param asOwner as type RequestList
     * @param asBorrower as type RequestList
     * @throws RemoteException
     */
    @Override
    public void receiveLogInResponse(String message, Student student, RequestList asOwner, RequestList asBorrower)
          throws RemoteException
    {
        model.receiveLogInResponse(message,student, asOwner, asBorrower);
    }
    /**
     * The method shows a book list
     * @param books as type ArrayList<Book>
     * @throws RemoteException
     */
    @Override
    public void receiveBookList(ArrayList<Book> books) throws RemoteException
    {
        model.receiveBookList(books);
    }
    /**
     * The method shows a book list after search
     * @param books as type ArrayList<Book>
     * @throws RemoteException
     */
    @Override
    public void receiveSearchResult(ArrayList<Book> books) throws RemoteException

    {
        model.receiveSearchResult(books);
    }
    /**
     * The method calls a request of type Request
     * @param request as Request
     * @throws RemoteException
     */
    @Override
    public void receiveRequest(Request request) throws RemoteException
    {
        model.receiveRequest(request);
    }
    /**
     * The method sends a request when it is one of the multiple requests on a book
     * @throws RemoteException
     */
    @Override
    public void receiveImposibleRequest() throws RemoteException
    {
        model.receiveImposibleRequest();
    }
    /**
     * The method sends an answer to a request on a book
     * @param request as type Request
     * @throws RemoteException
     */
    @Override
    public void receiveAnswerToRequest(Request request) throws RemoteException
    {
        model.receiveAswerToRequest(request);
    }

    /**
     * The method acceses the model method that signals that one of the user's
     * boorrowed book has been removed by the owner of the book as a sign of the
     * loan period end
     * @param book
     * @throws RemoteException
     */
    @Override
    public void borrowedBookRemovedNotification(Book book)
          throws RemoteException
    {
        model.borrowedBookRemovedNotification(book);
    }
    /**
     * The method does not allow a book to be removes from a user's booklist
     * @throws RemoteException
     */
    @Override
    public void receiveImposibleToRemove() throws RemoteException
    {
        model.receiveImposibleToRemove();
    }
    /**
     * The method updates the availability of the book
     * @param book as type Book
     * @throws RemoteException
     */
    @Override
    public void updateAvailability(Book book) throws RemoteException
    {
        server.updateAvailability(book, this);
    }

    /**
     * The method shows the request list
     * @param requestList as RequestList
     * @throws RemoteException
     */
    @Override
    public void receiveRequestList(RequestList requestList)
          throws RemoteException
    {
       
       model.receiveRequestList(requestList);
    }
}
