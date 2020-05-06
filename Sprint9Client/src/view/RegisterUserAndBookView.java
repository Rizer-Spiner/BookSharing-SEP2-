package view;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import viewmodel.RegisterUserAndBookViewModel;

/**
 * @Author Group 4
 */
public class RegisterUserAndBookView
{


   @FXML
   private Button cancelButton;
   @FXML
   private TextField nameField;
   @FXML
   private TextField passwordField;
   @FXML
   private TextField emailField;
   @FXML
   private TextField phoneField;
   @FXML
   private Button registerBookButton;
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
   private RegisterUserAndBookViewModel viewModel;

   public RegisterUserAndBookView()
   {

   }

   /**
    * initialize the registerUserAndBook window
    * and bind the properties with FXML elements.
    * @param viewModel
    * @param mainView
    * @param scene
    * @param title
    */
   public void init( RegisterUserAndBookViewModel viewModel,
                     MainView mainView,
                     Scene scene,
                     String title)
   {

      this.viewModel = viewModel;
      this.scene = scene;
      this.title = title;
      this.view = mainView;

      nameField.textProperty().bindBidirectional(viewModel.getNameProperty());
      passwordField.textProperty().bindBidirectional(viewModel.getPasswordProperty());
      emailField.textProperty().bindBidirectional(viewModel.getEmailProperty());
      phoneField.textProperty().bindBidirectional(viewModel.getPhoneProperty());
      titleField.textProperty().bindBidirectional(viewModel.getTitleProperty());
      authorField.textProperty().bindBidirectional(viewModel.getAuthorProperty());
      yearField.textProperty().bindBidirectional(viewModel.getYearProperty());

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
    * the action after clicking the register button is
    * it will redirect the student to the profile 
    * window in case of successful registration otherwise
    * it will show the error label
    */
   @FXML private void registerButtonPressed()
   {
      try
      {
         viewModel.register();
     
         view.setWindow("P");
      }
      catch(IllegalArgumentException e)
      {
         errorLabel.textProperty().set(e.getMessage());
      }
   }

   /**
    * in case od clicking cancel button the 
    * start window will be set.
    */
   @FXML private void cancelButtonPressed()
   {
      view.setWindow("START");
   }


}
