package model.mediator;

import model.domain.Book;
import utility.observer.listener.LocalListener;

import java.rmi.RemoteException;
import java.util.ArrayList;

public interface MVVMModelClient extends LocalListener<Object, Object>{

   
    void registerUser(String name, String password, String email, String phone,
          String title, String author, String year) throws RemoteException;
    BookModel getBookModel();
    StudentModel getStudentModel();
    public RequestModel getRequestModel();
    public ArrayList<Book> getLentBooks();
    public ArrayList<Book> getBorrowedBooks();
    public ArrayList<Book> getMyBooks();

   void validateLogin(String s, String s1);
} 