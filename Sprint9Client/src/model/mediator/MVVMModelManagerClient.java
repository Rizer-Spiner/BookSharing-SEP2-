package model.mediator;


import java.rmi.RemoteException;
import java.util.ArrayList;
import model.domain.Book;
import model.domain.BookList;
import model.domain.RequestList;
import model.domain.Student;
import utility.observer.event.ObserverEvent;



public class MVVMModelManagerClient implements MVVMModelClient
{
   
    private BookModel bookModel;
    private StudentModel studentModel;
    private RequestModel requestModel;
    private ModelClient adaptee;
    
    public MVVMModelManagerClient(ModelClient adaptee)
    {   
       
        this.studentModel = new StudentModel();
        this.bookModel = new BookModel(studentModel,adaptee);
        this.requestModel = new RequestModel(adaptee);
        this.adaptee = adaptee;   
    }
    
    
   /**
    * Added for MVVM
    * @return A list of the books that the local user owns and aren't lent out
    */
   @Override 
   public ArrayList<Book> getMyBooks()
    {
       ArrayList<Book> result = new ArrayList<>();
       if (studentModel.getLocalUser() == null) {

          return result;
       }
     
       for(Book book : bookModel.getBookList().getBookList())
       {
          if(book.getOwner().equals(studentModel.getLocalUser()) && book.getBorrower()==null)
          {
             
             Book item = new Book(
                   book.getTitle(),
                   book.getAuthor(),
                   book.getYear(),
                   studentModel.getLocalUser());
             
             result.add(item);
                   
          }
       }

       return result;
    }

   /**
    * Validates a logIn attempt with the String s and s1 passed as arguments.
    * A student is created and passed as argument for login
    * @param s
    * @param s1
    */
   @Override
   public void validateLogin(String s, String s1)
   {
      Student student= new Student(s,s1);
      try
      {
         adaptee.logIn(student);
      }
      catch (RemoteException e)
      {
         e.printStackTrace();
      }
   }

   /**
    * @return A list of books that local user does not owe, the borrower is local user
    */
   @Override
   public ArrayList<Book> getBorrowedBooks()
   {
      ArrayList<Book> result = new ArrayList<>();
      if (studentModel.getLocalUser() == null) {
         return result;
      }
      for(Book book : bookModel.getBookList().getBookList())
      {
         if(book.getBorrower().equals(studentModel.getLocalUser()) && book.getOwner()!=null)
         {

            
            Book item = new Book(
                  book.getTitle(),
                  book.getAuthor(),
                  book.getYear(),
                  studentModel.getLocalUser());
            
            result.add(item);      
         }   
      }
      
      return result;
      
   }
   /**
    * @return A list of books that local user owes, and the borrower is not null
    */
   @Override
   public ArrayList<Book> getLentBooks()
   {
      ArrayList<Book> result = new ArrayList<>();
      if (studentModel.getLocalUser() == null) {
         return result;
      }
      for(Book book : bookModel.getBookList().getBookList())
      {
         if(book.getOwner().equals(studentModel.getLocalUser()) && book.getBorrower()!=null)
         {

            
            Book item = new Book(
                  book.getTitle(),
                  book.getAuthor(),
                  book.getYear(),
                  studentModel.getLocalUser());
            
            result.add(item);        
         }   
      }
      
      return result;
      
   }
   
    /**
    * Create a student with passed arguments, create a book and set the book owner to student.
    * Set the local user in StudentModel to newly created student.
    * Call method registerNewUser() in \modelClient and pass student and book.
    */
   @Override
   public void registerUser(String name,String password, String email, String phone,String title,String author,String year) throws RemoteException
   {
      Student student = new Student(name,password,phone,email);
      Book book = new Book(title, author, year, student);
      studentModel.setLocalUser(student);
      adaptee.registerNewUser(student, book);

   
   }
      /**
    * Getter for bookModel instance.
    */
   @Override
   public BookModel getBookModel()
   {
      return bookModel;
   }
   /**
    * Getter for studentModel instance.
    */
   @Override
   public StudentModel getStudentModel()
   {
      return studentModel;
   }
    /**
    * Getter for requestModel instance.
    */
   @Override
   public RequestModel getRequestModel()
   {
      return requestModel;
   }
   /**
    * Implements switch, takes name of the event and calls methods
    * in BookModel or RequestModel, depending on event name.
    * If the name is "BOOKLIST", calls setBookList() method in BookModel.
    * If the name is "REQUEST", calls setRequests() method in RequestModel.
    * If the name is "REQUEST UPDATE", calls setRequests() method in RequestModel.
    * If the nameis "LOCAL USER", calls setLocalUser() in the StudentModel.
    */
   @Override
   public void propertyChange(ObserverEvent<Object, Object> event)
   {
      switch(event.getPropertyName())
      {
         case "BOOKLIST":
            bookModel.setBookList((BookList)event.getValue2());
            break;
         case "REQUEST":
            requestModel.setRequests((RequestList)event.getValue2());
            break;
         case "REQUEST UPDATE":
            requestModel.setRequests((RequestList)event.getValue2());
            break;
         case "LOCAL USER": studentModel.setLocalUser((Student) event.getValue2()); break;
            
      }
      
   }

   


   
}
