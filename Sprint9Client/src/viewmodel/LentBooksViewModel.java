package viewmodel;

import javafx.application.Platform;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import model.domain.Book;
import model.domain.BookList;
import model.mediator.MVVMModelClient;
import utility.observer.event.ObserverEvent;
import utility.observer.listener.LocalListener;

public class LentBooksViewModel implements LocalListener<BookList,BookList>
{

   private MVVMModelClient model;
   private ListProperty<Book> lentBooks;
   
   public LentBooksViewModel(MVVMModelClient model) 
   {
      this.model = model;
      this.lentBooks = new SimpleListProperty<Book>();

      model.getBookModel().addListener(this, "lent");
      this.lentBooks.set(FXCollections.observableArrayList(model.getLentBooks()));
      
   }
   /**
    * Getter for lentBooks.
    * @return lentBooks
    */
   public ListProperty<Book> getLentBookList()
   {
      return lentBooks;
   }
   /**
    * Calls getLentdBooks() in MVVMModelClient which gets the list.
    * Set lentBooks list to the fetched list.
    */
   @Override
   public void propertyChange(ObserverEvent<BookList, BookList> event)
   {
      Platform.runLater(new Runnable() {
      @Override
      public void run()
      {
         lentBooks.set(FXCollections.observableArrayList(model.getLentBooks()));
         
         
      }
      
   });
   }
      
   
   
   
}
