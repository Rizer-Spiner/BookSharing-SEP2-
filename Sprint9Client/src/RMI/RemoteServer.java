package RMI;

import model.domain.Book;
import model.domain.Request;
import model.domain.Student;


import java.rmi.Remote;
import java.rmi.RemoteException;



public interface RemoteServer extends Remote
{

   void registerNewUser(Student newStudent, Book book, RemoteClient rmiClient)
         throws RemoteException;

   void logIn(Student student, RemoteClient rmiClient)
         throws RemoteException;

   void logOff(Student localUser, RemoteClient rmiClient)
         throws RemoteException;

   void registerNewBook(Book book, RemoteClient rmiClient)
         throws RemoteException;

   void removeBook(Book book, RemoteClient rmiClient)
         throws RemoteException;

   void search(String category, String input, RemoteClient rmiClient)
         throws RemoteException;
   void receiveAnswerToRequest(Request request, RemoteClient rmiClient) throws RemoteException;
   void atemptBorrow(Request request, RemoteClient rmiClient)
         throws RemoteException;



   void updateAvailability(Book book, RemoteClient rmiClient)
         throws RemoteException;

}
