package viewmodel;

import java.util.List;

import javafx.application.Platform;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.domain.Book;
import model.domain.BookList;
import model.mediator.MVVMModelClient;
import utility.observer.event.ObserverEvent;
import utility.observer.listener.LocalListener;

public class BorrowedBooksViewModel implements LocalListener<BookList,BookList>
{

   private MVVMModelClient model;
   private ListProperty<Book> borrowedBooks;

   /**
    * Contructor. Creates a BoorrowedBookViewModel Object
    * @param model
    */
   public BorrowedBooksViewModel(MVVMModelClient model) 
   {
      this.model = model;
      this.borrowedBooks = new SimpleListProperty<Book>();
      model.getBookModel().addListener(this, "borrow");
      this.borrowedBooks.set(FXCollections.observableArrayList(model.getBorrowedBooks()));
      
   }
   /**
    * Getter for borrowedBooks.
    * @return borrowedBooks
    */
   public ListProperty<Book> getBorrowedBookList()
   {
      return borrowedBooks;
   }
   /**
    * Calls getBorrowedBooks() in MVVMModelClient which gets the list.
    * Sets borrowedBooks list to the fetched list.
    */
   @Override
   public void propertyChange(ObserverEvent<BookList, BookList> event)
   {
      Platform.runLater(new Runnable() {

         @Override
         public void run()
         {
            borrowedBooks.set(FXCollections.observableArrayList(model.getBorrowedBooks()));
            
         }
         
      });
      
      
   }
   

}
