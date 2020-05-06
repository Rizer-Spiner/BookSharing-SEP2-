package viewmodel;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import model.domain.Book;
import model.mediator.MVVMModelClient;


public class AddBookViewModel
{

   private StringProperty titleProperty;
   private StringProperty authorProperty;
   private StringProperty yearProperty;
   private MVVMModelClient model;
   
   public AddBookViewModel(MVVMModelClient model)
   {
      this.model = model;
      this.titleProperty = new SimpleStringProperty();
      this.authorProperty = new SimpleStringProperty();
      this.yearProperty = new SimpleStringProperty();

   }
   /**
    * Getter for titleProperty.
    * @return
    */
   public StringProperty getTitleProperty()
   {
      return titleProperty;
   }
    /**
    * Getter for authorProperty.
    * @return
    */
   public StringProperty getAuthorProperty()
   {
      return authorProperty;
   }
    /**
    * Getter for yearProperty.
    * @return
    */
   public StringProperty getYearProperty()
   {
      return yearProperty;
   }

   /**
    * Gets property values.
    * Validates constraints.
    * If any of the fields is empty or null, throws IllegalArgumentException.
    * If year field is other then 4 digits , throws IllegalArgumentException.
    */
   public void addBook()
   {
      
      String title = titleProperty.getValue();
      if(title == null || title.isEmpty())
      {
         throw new IllegalArgumentException("Title cannot be empty");    
      }
      String author = authorProperty.getValue();
      if(author == null || author.isEmpty())
      {
         throw new IllegalArgumentException("Author cannot be empty");    
      }
      String year = yearProperty.getValue();
      if(year == null || year.isEmpty())
      {
         throw new IllegalArgumentException("Year cannot be empty");    
      }
      if(year.length()!=4)
      {
         throw new IllegalArgumentException("Invalid year");    
      }
      try
      {
         Integer.parseInt(year);
      }
      catch(NumberFormatException e)
      {
         throw new IllegalArgumentException("Year is not a number",e);    
      }   
      Book book = new Book(title, author, year,model.getStudentModel().getLocalUser());
      
      try
      {
         model.getBookModel().addBook(book);
      }
      catch (Exception e1)
      {
         
         e1.printStackTrace();
      }

   }
  
   
}
