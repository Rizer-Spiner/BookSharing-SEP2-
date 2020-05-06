package model.mediator;

import RMI.RemoteClient;
import databasePersistence.BookDatabase;
import databasePersistence.BookPersistence;
import model.domain.*;

import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * ModelManagerServer implementation.
 * The class is responsable for modifing the server's data and return delivarables to
 * high-level entities (RMI SERVER) in the completion of a certain process in the system.
 *
 * @author Group 4
 * @version 1.5
 */
public class ModelManagerServer implements ModelServer
{

   private UserList onlineUsers;
   private StudentList allUsers;
   private Library library;
   private RequestList requestList;

   private BookPersistence database;

   /**
    * Contructor for the ModelManagerServer object.
    * It initializes all instance variables and sets the RequestList, StudentList
    * and Library with the data loaded from the BookDatabase object (database).
    *
    * @see #setAllUsers(StudentList)
    * @see #setRequestList(RequestList)
    * @see #setLibrary()
    */
   public ModelManagerServer()
   {
      this.database = new BookDatabase();
      this.onlineUsers = new UserList();

      this.requestList = new RequestList();
      setRequestList();
      this.allUsers = new StudentList();
      setAllUsers();
      this.library = new Library();
      setLibrary();

   }

   /**
    * Sets the Library data by loading it from database
    *
    * @see BookDatabase#loadAllBooks()
    */

   private void setLibrary()
   {
      BookList libraryList = null;
      try
      {
         libraryList = database.loadAllBooks();
      }
      catch (SQLException e)
      {
         e.printStackTrace();
      }
      ArrayList<Book> bookArrayList = libraryList.getBookList();

      for (int i = 0; i < bookArrayList.size(); i++)
      {
         library.addToLibrary(bookArrayList.get(i));
      }

   }

   /**
    * Sets the Requestlist data by loading it from database.
    *
    * @see BookDatabase#loadRequest()
    */
   private void setRequestList()
   {
      try
      {
         requestList = database.loadRequest();
      }
      catch (SQLException e)
      {
         e.printStackTrace();
      }

   }

   /**
    * Sets the StudentList data, each Student with their respective BookList
    * information by loading it from database
    *
    * @see BookDatabase#loadPerson()
    * @see BookDatabase#loadBook(String)
    * @see BookDatabase#loadBorrowedBooks(String)
    */
   private void setAllUsers()
   {
      try
      {
         this.allUsers = database.loadPerson();
      }
      catch (SQLException e)
      {
         e.printStackTrace();
      }
      for (int i = 0; i < allUsers.size(); i++)
      {
         try
         {
            allUsers.getStudents().get(i).setBookList(database
                  .loadBook(allUsers.getStudents().get(i).getUsername()));
            allUsers.getStudents().get(i).addToBookList(database
                  .loadBorrowedBooks(
                        allUsers.getStudents().get(i).getUsername()));

         }
         catch (SQLException e)
         {
            e.printStackTrace();
         }

      }

   }

   /**
    * Returns a Library object containing the Servers's model Library data
    *
    * @return library
    */
   @Override
   public Library getLibrary()
   {
      return this.library;
   }

   /**
    * Adds an new Online user to the Userlist
    *
    * @param newStudent
    * @param rmiClient
    */
   @Override
   public void registerNewOnlineUser(Student newStudent, RemoteClient rmiClient)
   {

      onlineUsers.addStudent(newStudent, rmiClient);

   }

   /**
    * Returns a boolean specifing if the Student passed as argument in the method
    * exists as part of the Online Users UserList. It loops though the UserList
    * with the intention of checking if there is a User of which Student equals
    * the Student passed in the method.
    *
    * @param student
    * @return boolean
    */
   @Override
   public boolean verifyIfUserIsOnline(Student student)
   {
      for (int i = 0; i < onlineUsers.size(); i++)
      {
         if (student.equalsForLogin(onlineUsers.getUsers().get(i).getStudent()))
         {
            return true;
         }
      }
      return false;
   }

   /**
    * Returns a RequestList object of Requests of which lender is equal to the
    * Student passed as argument.
    *
    * @param student
    * @return RequestList
    */
   @Override
   public RequestList getRequestListasOwner(Student student)
   {
      RequestList requests = new RequestList();
      for (int i = 0; i < requestList.size(); i++)
      {
         if (student.equals(requestList.getRequests().get(i).getLender())
               && (requestList.getRequests().get(i).getStatus()
               .equals("waiting")))
         {
            requests.add(requestList.getRequests().get(i));
         }
      }
      return requests;
   }

   /**
    * Returns a Requestlist Object containing Requests of which borrower is equal
    * to the Student Object passed as argument. All matches are removed as they are no longer needed
    * both in the java model as well in the database.
    *
    * @param student
    * @return RequestList
    * @throws SQLException
    * @see BookDatabase#removeRequest(Request)
    */

   @Override
   public RequestList getRequestListAsBorrower(Student student)

   {
      RequestList requests = new RequestList();
      for (int i = 0; i < requestList.size(); i++)
      {
         if (student.equals(requestList.getRequests().get(i).getBorrower())
               && (!(requestList.getRequests().get(i).getStatus()
               .equals("waiting"))))
         {
            requests.add(requestList.getRequests().get(i));
            try
            {
               database.removeRequest(requestList.getRequests().get(i));
            }
            catch (SQLException e)
            {
               e.printStackTrace();
            }
            requestList.getRequests().remove(i);

         }
      }
      return requests;
   }

   /**
    * Removes one user from the Userlist
    *
    * @param localUser
    */
   @Override
   public void removeOnlineUser(Student localUser)
   {

      onlineUsers.removeStudent(localUser);
   }

   /**
    * Adds one Book to a specific Student BookList.
    * The specific Student is selected by calling the method .getOwner()
    * on the Book object.
    * The Book is also added to the Library and database
    *
    * @param book
    * @see Book#getOwner()
    * @see BookDatabase#registerBook(Book)
    */
   @Override
   public void registerNewBook(Book book)
   {
      Student toWhichStudentToRegister = book.getOwner();

      if (toWhichStudentToRegister == null)
      {
         throw new IllegalStateException();
      }
      else
      {
         library.addToLibrary(book);
         try
         {
            database.registerBook(book);
         }
         catch (SQLException e)
         {
            e.printStackTrace();
         }

         for (int i = 0; i < allUsers.size(); i++)
         {
            if (toWhichStudentToRegister.equals(allUsers.getStudents().get(i)))
            {
               allUsers.getStudents().get(i).registerNewBook(book);

            }
         }
      }

   }

   /**
    * Returns an ArrayList<> representation of one Student BookList by searching
    * Book matches that have as owner the Student passed as argument.
    *
    * @param owner
    * @return ArrayList<Book>
    */
   @Override
   public ArrayList<Book> getBookListOfUser(Student owner)
   {
      ArrayList<Book> books = new ArrayList<>();

      for (int i = 0; i < allUsers.size(); i++)
      {
         if (owner.equals(allUsers.getStudents().get(i)))
         {
            books = allUsers.getStudents().get(i).getBookList().getBookList();

         }
      }
      return books;

   }

   /**
    * Removes a Book from a specific Student selected using the method .getOwner()
    * on the Book object. The Book also is removed from the Library an Database.
    * Returns false is the BookList size will be less then 1.
    *
    * @param book
    * @return boolean
    */

   @Override
   public boolean removeBook(Book book)
   {

      Student toWhichStudentToRemove = book.getOwner();

      try
      {
         database.removeBook(book);
      }
      catch (SQLException e)
      {
         e.printStackTrace();
      }
      library.removeFromLibrary(book);

      for (int i = 0; i < allUsers.size(); i++)
      {
         if (toWhichStudentToRemove.equals(allUsers.getStudents().get(i)))
         {
            if (allUsers.getStudents().get(i).getBookList().size() > 1)
            {
               allUsers.getStudents().get(i).removeBook(book);

               return true;
            }

         }
      }

      return false;
   }

   /**
    * Returns an Arraylist<> of Books tha match/ are equal to the Book argument
    *
    * @param book
    * @return ArrayList</ Book>
    */
   @Override
   public ArrayList<Book> getSearchResult(Book book)
   {
      return library.getSearchResult(book);
   }

   /**
    * Returns the UserList containing online Users
    *
    * @return UserList
    */
   public UserList getOnlineUsers()
   {
      return onlineUsers;
   }

   /**
    * Returns an Arraylist containing Books matching the description given by the input
    * argument depending on the category chosen.
    *
    * @param category
    * @param input
    * @return ArrayList</ Book>
    */
   @Override
   public ArrayList<Book> getSearchResult(String category, String input)
   {

      return library.getSearchResult(category, input);
   }

   /**
    * Returns true is the the RequestList object of server contains Request
    * that include the Book object passed as argument. Returns false in contrary case.
    *
    * @param book
    * @return boolean
    */
   @Override
   public boolean bookHasRequest(Book book)
   {
      for (int i = 0; i < requestList.size(); i++)
      {
         if (book.equals(requestList.getRequests().get(i).getBook()))
         {
            return true;
         }
      }
      return false;
   }

   /**
    * Adds a Request to the RequestList and database
    *
    * @param request
    */
   @Override
   public void addRequest(Request request)
   {
      requestList.add(request);
      try
      {
         database.addRequest(request);
      }
      catch (SQLException e)
      {
         e.printStackTrace();
      }
   }

   /**
    * Returns a Proxy Stup remote object depicting the RemoteClient remote interface
    * using the Student object passed as argument in the UserList.
    *
    * @param borrower
    * @return RemoteClient
    */
   @Override
   public RemoteClient getAssociatedClient(Student borrower)
   {
      for (int i = 0; i < onlineUsers.size(); i++)
      {
         if (borrower.equals(onlineUsers.getUsers().get(i).getStudent()))
         {
            return onlineUsers.getUsers().get(i).getAssociatedClient();
         }
      }
      return null;
   }

   /**
    * Modifies the status of an already existing Request in the RequestList using the
    * equal() method. The change also happens in database.
    *
    * @param request
    */

   @Override
   public void changeRequestForAnswer(Request request)
   {
      for (int i = 0; i < requestList.size(); i++)
      {

         if (request.equals(requestList.getRequests().get(i)))
         {

            requestList.getRequests().remove(i);
            requestList.add(request);
            if (request.getStatus().equals("accepted"))
            {
               try
               {
                  database.updateStatusToAccepted(request);

               }
               catch (SQLException e)
               {
                  e.printStackTrace();
               }
               try
               {
                  database.moveRequestToLoan(request);

               }
               catch (SQLException e)
               {
                  e.printStackTrace();
               }
            }
            else
            {
               try
               {
                  database.updateStatusToDenied(request);
               }
               catch (SQLException e)
               {
                  e.printStackTrace();
               }
            }
         }
         else
         {

         }
      }
   }

   /**
    * Adds the Book object passed as argument into the Student object passed as argument
    * BookList with the availability false. Sets the availability false for the Book object in its
    * owner BookList. Sets the availability false for the Book object in the Library.
    *
    * @param borrower
    * @param book
    */
   @Override
   public void borrow(Student borrower, Book book)
   {
      allUsers.borrow(borrower, book);
      library.borrow(book);
   }

   /**
    * Returns false is there is at least one existing Student in the StudentList
    * equal to the Student object passed as argument. Otherwise the method returns
    * true.
    *
    * @param newStudent
    * @return boolean
    */
   @Override
   public boolean verifyIfUserIsUnique(Student newStudent)
   {
      for (int i = 0; i < allUsers.size(); i++)
      {
         if (newStudent.equalsForRegistration(allUsers.getStudents().get(i)))
         {
            return false;
         }
      }
      return true;
   }

   /***
    *
    * Returns true if there is at least one Student object in the studentList
    * equal to the Student object passed as argument. Returns false otherwise.
    *
    * @param student
    * @return boolean
    */
   @Override
   public boolean verifyIfUserExists(Student student)
   {
      for (int i = 0; i < allUsers.size(); i++)
      {
         if (student.equalsForLogin(allUsers.getStudents().get(i)))
         {
            return true;
         }
      }
      return false;

   }

   /**
    * Sets the online users UserList
    *
    * @param onlineUsers
    */
   @Override
   public void setOnlineUsers(UserList onlineUsers)
   {
      this.onlineUsers = onlineUsers;
   }

   /**
    * Returns a StudentList object containing all Students.
    *
    * @return StudentList
    */
   @Override
   public StudentList getAllUsers()
   {
      return allUsers;
   }

   /**
    * Sets the StudentList with tha data passed as argument.
    *
    * @param allUsers
    */
   @Override
   public void setAllUsers(StudentList allUsers)
   {
      this.allUsers = allUsers;
   }

   /**
    * Returns a RequestList object containing all requests.
    *
    * @return Requestlist
    */
   @Override
   public RequestList getRequestList()
   {
      return requestList;
   }

   /**
    * Sets the Requestlist instance.
    *
    * @param requestList
    */
   @Override
   public void setRequestList(RequestList requestList)
   {
      this.requestList = requestList;
   }

   /**
    * Add a new Student object to the StudentList with the Book object added to its
    * BookList. The insert is applied also to database.
    *
    * @param newStudent
    * @param book
    * @see BookDatabase#registerPerson(Student, Book)
    */
   @Override
   public void registerUser(Student newStudent, Book book)
   {
      allUsers.addStudent(newStudent);
      try
      {
         database.registerPerson(newStudent, book);
      }
      catch (SQLException e)
      {
         e.printStackTrace();
      }

   }

   /**
    * Returns a student object equivalent to the Student object passed as
    * argument. The difference between them is that the returned value is part of the server's
    * data, containig more information regarding the BookList of the Student.
    * In case of no match the method return null.
    *
    * @param student
    * @return Student
    */
   @Override
   public Student getStudent(Student student)
   {
      for (int i = 0; i < allUsers.size(); i++)
      {
         if (student.equalsForLogin(allUsers.getStudents().get(i)))
         {
            return allUsers.getStudents().get(i);
         }
      }
      return null;
   }

   /**
    * Removes a request from the java model and database.
    *
    * @param request
    */
   @Override
   public void removeRequest(Request request)
   {
      for (int i = 0; i < requestList.size(); i++)
      {
         if (request.equals(requestList.getRequests().get(i)))
         {
            requestList.remove(i);
            try
            {
               database.removeRequest(request);
            }
            catch (SQLException e)
            {
               e.printStackTrace();
            }
         }
      }
   }

   /**
    * The Book availability is set to true in the java model and database.
    * The Book is removed from all BookLists that contains it and added again
    * only in its owner's BookList.
    *
    * @param book
    * @throws RemoteException
    */
   @Override
   public void updateAvailability(Book book) throws RemoteException
   {
      for (int i = 0; i < allUsers.size(); i++)
      {
         for (int j = 0;
              j < allUsers.getStudents().get(i).getBookList().size(); j++)
         {
            if (book.equals(
                  allUsers.getStudents().get(i).getBookList().getBookList()
                        .get(j)))
            {
               allUsers.getStudents().get(i).getBookList().getBookList()
                     .remove(j);
            }
         }

      }

      for (int k = 0; k < allUsers.size(); k++)
      {
         if (book.getOwner().equals(allUsers.getStudents().get(k)))
         {
            allUsers.getStudents().get(k).registerNewBook(book);

         }
      }

      try
      {
         database.removeFromLoan(book);
      }
      catch (SQLException e)
      {
         e.printStackTrace();
      }

   }

}
