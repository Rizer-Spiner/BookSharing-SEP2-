package view;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import viewmodel.StartViewModel;

public class StartView
{
   @FXML
   private Button logInButton;
   @FXML
   private Button registerButton;
   @FXML
   private Button searchButton;

   private String title;
   private Scene scene;
   private MainView view;
   private StartViewModel viewModel;

   public StartView()
   {

   }

   /**
    * initialize the start view.
    * @param viewModel
    * @param mainView
    * @param scene
    * @param title
    */
   public void init( StartViewModel viewModel,
                     MainView mainView,
                     Scene scene,
                     String title)
   {

      this.viewModel = viewModel;
      this.scene = scene;
      this.title = title;
      this.view = mainView;


   }

   /**
    * 
    * @return title
    */
   public String getTitle()
   {
      return title;
   }

   /**
    * 
    * @return scene
    */
   public Scene getScene()
   {
      return scene;
   }

   /**
    * opens the login window
    */
   @FXML private void logInButtonPressed()
   {
      view.setWindow("LOG");
   }

   /**
    * opens the register user window
    */
   @FXML private void registerButtonPressed()
   {

      view.setWindow("REGISTER USER");


   }

   @FXML private void searchButtonPressed()
   {

   }



}
