package viewmodel;



import java.rmi.RemoteException;

import javafx.application.Platform;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import model.domain.Book;
import model.domain.BookList;
import model.mediator.MVVMModelClient;
import utility.observer.event.ObserverEvent;
import utility.observer.listener.LocalListener;

public class MyBooksViewModel implements LocalListener<BookList,BookList>
{
   private MVVMModelClient model;
   private ListProperty<Book> mybookList;
   
   public MyBooksViewModel(MVVMModelClient model)
   {
      this.model = model;
      this.mybookList = new SimpleListProperty<Book>();
      model.getBookModel().addListener(this, "booklist");
      this.mybookList.set(FXCollections.observableArrayList(model.getMyBooks()));
   }
   /**
    * Returns users book list.
    * @return mybookList
    */
   public ListProperty<Book> getMybookList()
   {
      return mybookList;
   }
   /**
    * Calls getMyBooks() in MVVMModelClient which gets the list.
    * Sets My book list to the fetched list.
    */
   @Override
   public void propertyChange(ObserverEvent<BookList, BookList> event)
   {
    
      Platform.runLater(new Runnable() {

         @Override
         public void run()
         {
            mybookList.set(FXCollections.observableArrayList(model.getMyBooks()));
            
         }
         
      });
      
   }
   /**
    * Calls getBookModel() from MVVMModelClient
    * Calls deleteBook() in the BookModel.
    * @param item
    * @throws RemoteException
    */
   public void removeBook(Book item) throws RemoteException
   {
      model.getBookModel().deleteBook(item);
      
   }
  
   
}
