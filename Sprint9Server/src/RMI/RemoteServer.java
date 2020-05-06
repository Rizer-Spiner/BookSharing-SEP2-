package RMI;

import model.domain.Book;
import model.domain.Request;
import model.domain.Student;


import java.rmi.Remote;
import java.rmi.RemoteException;
import java.sql.SQLException;



public interface RemoteServer extends Remote
{

   void registerNewUser(Student newStudent, Book book, RemoteClient rmiClient)
         throws RemoteException, SQLException;

   void logIn(Student student, RemoteClient rmiClient)
         throws RemoteException, SQLException;

   void logOff(Student localUser, RemoteClient rmiClient)
         throws RemoteException;

   void registerNewBook(Book book, RemoteClient rmiClient)
         throws RemoteException, SQLException;

   void removeBook(Book book, RemoteClient rmiClient)
         throws RemoteException, SQLException;

   void search(String category, String input, RemoteClient rmiClient)
         throws RemoteException;
   void receiveAnswerToRequest(Request request, RemoteClient rmiClient) throws RemoteException;
   void atemptBorrow(Request request, RemoteClient rmiClient)
         throws RemoteException, SQLException;


   void updateAvailability(Book book, RemoteClient rmiClient)
         throws RemoteException, SQLException;
   
   

}
