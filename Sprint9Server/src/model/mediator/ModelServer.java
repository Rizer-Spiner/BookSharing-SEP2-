package model.mediator;


import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;

import RMI.RemoteClient;
import model.domain.*;

public interface ModelServer {
   Library getLibrary();

   void setOnlineUsers(UserList onlineUsers);

   StudentList getAllUsers();

   void registerNewOnlineUser(Student newStudent, RemoteClient rmiClient);

   boolean verifyIfUserIsOnline(Student student);

   RequestList getRequestListasOwner(Student student);

   RequestList getRequestListAsBorrower(Student student);

   void removeOnlineUser(Student localUser);

   void registerNewBook(Book book) throws SQLException;

   ArrayList<Book> getBookListOfUser(Student owner);

   boolean removeBook(Book book) throws SQLException;

   ArrayList<Book> getSearchResult(Book book);

   UserList getOnlineUsers();

   boolean bookHasRequest(Book book);

   void addRequest(Request request) throws SQLException;

   RemoteClient getAssociatedClient(Student borrower);

   void changeRequestForAnswer(Request request) throws SQLException;

   void borrow(Student borrower, Book book);

   boolean verifyIfUserIsUnique(Student newStudent);

   boolean verifyIfUserExists(Student student);


   void setAllUsers(StudentList allUsers);

   RequestList getRequestList();


   void setRequestList(RequestList requestList);

   void registerUser(Student newStudent, Book book) throws SQLException;

   Student getStudent(Student student);

   void removeRequest(Request request) throws SQLException;

   void updateAvailability(Book book) throws RemoteException, SQLException;


   ArrayList<Book> getSearchResult(String category, String input);


}
