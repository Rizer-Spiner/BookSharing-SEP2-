package viewmodel;

import javafx.beans.property.Property;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import model.mediator.MVVMModelClient;

public class SearchViewModel
{
   private StringProperty searchProperty;
   private MVVMModelClient model;
   
   public SearchViewModel(MVVMModelClient model)
   {
      this.model = model;
      this.searchProperty = new SimpleStringProperty();
      
   }

   /**
    * Getter for the search Property
    * @return Property</String>
    */
   public Property<String> getSearchProperty()
   {
     return searchProperty;
   } 

}
