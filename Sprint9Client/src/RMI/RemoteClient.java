package RMI;
import model.domain.Book;
import model.domain.Request;
import model.domain.RequestList;
import model.domain.Student;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;

public interface RemoteClient extends Remote {
    void registerNewUser(Student newStudent, Book book)
          throws RemoteException;
    void logIn(Student student) throws RemoteException;
    void logOff(Student localUser) throws RemoteException;
    void registerNewBook(Book book) throws RemoteException;
    void removeBook(Book book) throws RemoteException;
    void search(String category, String input) throws RemoteException;
    void attemptBorrow(Request request) throws RemoteException;
    void sendAnswerToRequest(Request request)
          throws RemoteException;
    void updateAvailability(Book book) throws RemoteException;


    void receiveRegistrationMessage(String message, Student newStudent) throws RemoteException;
    void receiveLogInResponse(String message, Student student,
          RequestList asOwner, RequestList asBorrower) throws RemoteException;

    void receiveBookList(ArrayList<Book> books) throws RemoteException;
    void receiveSearchResult(ArrayList<Book> books) throws RemoteException;

    void receiveRequest(Request request) throws RemoteException;

    void receiveImposibleRequest() throws RemoteException;
    void receiveAnswerToRequest(Request request) throws RemoteException;

    void borrowedBookRemovedNotification(Book book)
          throws RemoteException;

    void receiveImposibleToRemove() throws RemoteException;
    void receiveRequestList(RequestList requestList)
          throws RemoteException;


}
