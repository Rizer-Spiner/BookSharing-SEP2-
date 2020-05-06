package model.mediator;

import java.rmi.RemoteException;

import model.domain.Book;
import model.domain.BookList;
import utility.observer.listener.GeneralListener;
import utility.observer.subject.LocalSubject;
import utility.observer.subject.PropertyChangeProxy;


public class BookModel implements LocalSubject<BookList,BookList>
{

   private PropertyChangeProxy<BookList,BookList> property;
   private BookList bookList;
   private StudentModel studentModel;
   private ModelClient adaptee;
   
   public BookModel(StudentModel studentModel, ModelClient adaptee)
   {
      this.studentModel  = studentModel;
      this.adaptee = adaptee;
      this.property = new PropertyChangeProxy<>(this);
      this.bookList = new BookList();
      
      
   }

   /**
    *
    * Adds the listner passed as argument as listener to events emitted by the class
    *
    * @param listener
    * @param propertyNames
    * @return boolean
    */
   @Override
   public boolean addListener(GeneralListener<BookList, BookList> listener,
         String... propertyNames)
   {
      return property.addListener(listener, propertyNames);
   }
   /**
    *
    * Removes the listner passed as argument as listener to events emitted by the class
    *
    * @param listener
    * @param propertyNames
    * @return boolean
    */
   @Override
   public boolean removeListener(GeneralListener<BookList, BookList> listener,
         String... propertyNames)
   {
      return property.removeListener(listener, propertyNames);
   }
   /**
    * Calls register new registerNewBook() method in ModelClient.
    * @param item
    * @throws RemoteException
    */
   public void addBook(Book item) throws RemoteException
   {
      adaptee.registerNewBook(item);

   }
   /**
    * Calls deleteBook() method in ModelClient.
    * @param item
    * @throws RemoteException
    */
   public void deleteBook(Book item) throws RemoteException
   {
      adaptee.removeBook(item);

   }

   /**
    * Getter for user's book list.
    * @return bookList
    */
   public BookList getBookList()
   {
      return bookList;
   }
   /**
    * Setter for user's book list.
    * @param list
    */
   public void setBookList(BookList list)
   {
      this.bookList = list;
      property.firePropertyChange("booklist", null, list);
   }

   /**
    * Removes a Book from the user's BookList
    * @param item
    */
   public void removeBook(Book item)
   {
      Book removable = null;
     for(Book book : bookList.getBookList())
     {
        if(book.getTitle().equals(item.getTitle()) &&
              book.getAuthor().equals(item.getAuthor()) &&
              book.getYear().equals(item.getYear()))
                    {
           removable = book;
           break;
                 
                    }
     }
     if(removable!=null)
     {
        bookList.removeBook(removable);     }

      
   }
   
   

}
