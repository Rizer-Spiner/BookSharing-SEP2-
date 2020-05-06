package test;

import model.domain.Book;
import model.domain.Request;
import model.domain.RequestList;
import model.domain.Student;
import utility.observer.subject.LocalSubject;

import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;

public interface ModelClient extends LocalSubject<Object,Object>
{

    void registerNewUser(Student newStudent, Book book)
          throws RemoteException, SQLException;
    void logIn(Student student) throws RemoteException, SQLException;
    void logOff() throws RemoteException;
    void registerNewBook(Book book) throws RemoteException, SQLException;
    void removeBook(Book book) throws RemoteException, SQLException;
    void search(String category, String input)throws RemoteException;
    void attemptBorrow(Book book) throws RemoteException;
    void sendAnswerToRequest(Request request)
          throws RemoteException, SQLException;

    void updateAvailability(Book book) throws RemoteException, SQLException;









    void receiveRegistrationMessage(String message, Student newStudent) throws RemoteException;
    void receiveLogInResponse(String message, Student student,
          RequestList asOwner, RequestList asBorrow) throws RemoteException;
    void receiveBookList(ArrayList<Book> books) throws RemoteException;
    void receiveSearchResult(ArrayList<Book> books) throws RemoteException;
    void receiveRequest(Request request) throws RemoteException;
    void receiveAswerToRequest(Request request) throws RemoteException;
    void receiveImposibleRequest() throws  RemoteException;
    void receiveWishNotification(Book book) throws RemoteException;
    void borrowedBookRemovedNotification(Book book) throws RemoteException;
    void receiveImposibleToRemove() throws RemoteException;
}
