package view;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import viewmodel.SearchViewModel;

public class SearchView
{
   @FXML private TextField searchField;
   @FXML private Button searchButton;
   @FXML private ListView<String> searchList;
   @FXML private Button requestButton;
   @FXML private Button cancelButton;
   
   private Scene scene;
   private String title;
   private MainView mainView;
   private SearchViewModel searchViewModel;
   public SearchView()
   {
      
   }
   public void init( SearchViewModel viewModel,
         MainView mainView,
         Scene scene, 
         String title)
   {

   this.searchViewModel = viewModel;
   this.scene = scene;
   this.title = title;
   this.mainView = mainView;

   searchField.textProperty().bindBidirectional(viewModel.getSearchProperty());
   

   }
   public String getTitle()
   {
      return title;
   }

   
   public Scene getScene()
   {
      return scene;
   }
   @FXML public void searchButtonClicked()
   {}
   @FXML public void requestButtonClicked()
   {}
   @FXML public void cancelButtonClicked()
   {}

}
