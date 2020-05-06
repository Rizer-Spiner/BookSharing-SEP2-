package databasePersistence;

import model.domain.*;

import java.sql.SQLException;
import java.util.ArrayList;

public interface BookPersistence {
    StudentList loadPerson() throws SQLException;
    BookList loadBook(String username) throws SQLException;

    BookList loadAllBooks() throws SQLException;


   ArrayList<Book> loadBorrowedBooks(String username);

   RequestList loadRequest() throws SQLException;

    void registerBook(Book book) throws SQLException;

    void removeBook(Book book) throws SQLException;

    void registerPerson(Student student, Book book) throws SQLException;

    String getContactInfo(String username) throws SQLException;
    
    String getContactInfo(Student username) throws SQLException;


    void addRequest(Request request) throws SQLException;

    void removeRequest(Request request) throws SQLException;

    void removeStudent(Student student) throws SQLException;

    void removeDeniedRequest(Request request) throws SQLException;

    void updateStatusToAccepted(Request request) throws SQLException;

    void moveRequestToLoan(Request request) throws SQLException;

    void removeFromLoan(Book book) throws SQLException;

    void updateStatusToDenied(Request request) throws SQLException;
}
