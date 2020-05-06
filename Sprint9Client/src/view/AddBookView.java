package view;



import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import viewmodel.AddBookViewModel;
/**
*
*@Author Gorup 4
*/
public class AddBookView
{
   @FXML
   private Button addButton;
   @FXML
   private Button cancelButton;
   @FXML
   private TextField titleField;
   @FXML
   private TextField authorField;
   @FXML
   private TextField yearField;
   @FXML
   private Label errorLabel;
   
   
   private String title;
   private Scene scene;
   private MainView view;
   private AddBookViewModel viewModel;
   
   public AddBookView()
   {
      
   }
   /**
    * Initialize AddBookView.
    * Bind the properties with FXML elements.
    * @param viewModel
    * @param mainView
    * @param scene
    * @param title
    */
   public void init( AddBookViewModel viewModel,
         MainView mainView,
         Scene scene, 
         String title)
   {

   this.viewModel = viewModel;
   this.scene = scene;
   this.title = title;
   this.view = mainView;
   
   titleField.textProperty().bindBidirectional(viewModel.getTitleProperty());
   authorField.textProperty().bindBidirectional(viewModel.getAuthorProperty());
   yearField.textProperty().bindBidirectional(viewModel.getYearProperty());
   
   }
   /**
    * Getter for title.
    * @return title
    */
   public String getTitle()
   {
      return title;
   }
    /**
    * Getter for scene.
    * @return scene
    */
   public Scene getScene()
   {
      return scene;
   }
   
   /**
    * Actions after add button is clicked:
    * Calls addBook() in AddBookViewModel.
    * Calls setWindow() method in MainView which opens Profile window.
    * Clears out the fields. 
    */
   @FXML public void addButtonClicked()
   {
      try
      {
      viewModel.addBook();

      view.setWindow("P");
      titleField.clear();
      authorField.clear();
      yearField.clear();
      }
      catch(IllegalArgumentException e)
      {
         errorLabel.textProperty().set(e.getMessage());
      }
   }
   /**
    * Actions after add button is clicked:
    * Calls setWindow() method in MainView which opens Profile window.
    */
   @FXML public void cancelButtonClicked()
   {

     view.setWindow("P");
     
   }
  
  

}
